package dtu.amd.worktracker.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dtu.amd.worktracker.dal.WorkRepositoryImpl
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.repository.DataStoreRepository
import dtu.amd.worktracker.repository.DataStoreRepositoryImpl
import dtu.amd.worktracker.util.*
import dtu.amd.worktracker.util.DATES.listOfMonths
import dtu.amd.worktracker.util.DATES.listOfYears
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

// A Hilt View Model is a Jetpack ViewModel that is constructor injected by Hilt.
// To enable injection of a ViewModel by Hilt use the @HiltViewModel annotation
@HiltViewModel
class AddViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
    private val pref: DataStoreRepository
) : ViewModel() {


    // Variables for the input fields
    var title by mutableStateOf("")
    var company by mutableStateOf("")
    var date by mutableStateOf(Date())
    var start by mutableStateOf(Date())
    var end by mutableStateOf(Date())
    var lunch_held by mutableStateOf(true)
    var lunch_start by mutableStateOf(Date())
    var lunch_end by mutableStateOf(Date())
    var hourly_rate by mutableStateOf("")
    var salary_period_month by mutableStateOf(Date().AsMonth())
    var salary_period_year by mutableStateOf(Date().AsYear())

    var months: List<String> = listOfMonths

    var years: List<String> = listOfYears

    init {
        getSalaryPeriod()
        getSalary()
    }

    // Get the salary period from the data store
    fun getSalaryPeriod() = runBlocking {
        val period = pref.getSalaryPeriod()
        salary_period_month = period[1]
        salary_period_year = period[0]
    }

    // Get the hourly rate from the data store
    fun getSalary() = runBlocking {
        val prefPaid = pref.getDouble(PREF_KEYS.SALARY, 0.0)
        if (prefPaid == 0.0) {
            hourly_rate = ""
        } else {
            hourly_rate = prefPaid.toString()
        }
    }

    // SOURCE: https://proandroiddev.com/the-big-form-with-jetpack-compose-7bec9cde157e
    fun showDatePickerDialog(context: Context) {
        val calendar = Calendar.getInstance()
        // Set the calendar to the current date
        calendar.time = date

        DatePickerDialog(
            context, { _, year, month, day ->
                date = getPickedDateAsDate(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
    }

    fun showTimePickerDialog(context: Context, type: String) {
        val calendar = Calendar.getInstance()
        // Set the calendar to the current date
        // This picker is used by multiple fields, so we need to check which one is being used
        when (type) {
            "start" -> {
                calendar.time = start
            }
            "end" -> {
                calendar.time = end
            }
            "lunch_start" -> {
                calendar.time = lunch_start
            }
            "lunch_end" -> {
                calendar.time = lunch_end
            }
        }
        TimePickerDialog(
            context,
            { _, hour, minute ->

                when (type) {
                    "start" -> {
                        start = getPickedTimeAsTime(hour, minute)
                    }
                    "end" -> {
                        end = getPickedTimeAsTime(hour, minute)
                    }
                    "lunch_start" -> {
                        lunch_start = getPickedTimeAsTime(hour, minute)
                    }
                    "lunch_end" -> {
                        lunch_end = getPickedTimeAsTime(hour, minute)
                    }
                }

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
        ).show()
    }

    // Format picked time as a Date object
    private fun getPickedTimeAsTime(hour: Int, minute: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.time
    }

    // Format picked date as a Date object
    private fun getPickedDateAsDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar.time
    }

    // Add the work to the database
    fun save() {
        // Calculate the total hours
        val hours = start.getDiffInHours(end, lunch_held, lunch_start, lunch_end)
        // If hourly rate is empty set it to 0
        if (hourly_rate == "") {
            hourly_rate = "0"
        }
        val work = Work(
            id = 0,
            title = title,
            company = company,
            date = date,
            start = start,
            end = end,
            lunch_held = lunch_held,
            lunch_start = lunch_start,
            lunch_end = lunch_end,
            paid = hourly_rate.toDouble() * hours,
            hourly_rate = hourly_rate.toDouble(),
            hours = hours,
            salary_period_month = salary_period_month,
            salary_period_year = salary_period_year
        )

        // If title is empty, set it to default calculated value
        if (work.title.isEmpty() && work.company.isNotEmpty()) {
            work.title = "Shift at ${work.company}"
        } else if (work.title.isEmpty() && work.company.isEmpty()) {
            work.title = "Shift at ${work.date.AsDate()}"
        }

        // Save the work to the database
        workRepositoryImpl.addWork(work)
    }

}