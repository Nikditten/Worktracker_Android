package dtu.amd.worktracker.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepositoryImpl
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.util.*
import dtu.amd.worktracker.util.DATES.listOfMonths
import dtu.amd.worktracker.util.DATES.listOfYears
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject


@HiltViewModel
class EditViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
    // SOURCE: https://github.com/philipplackner/MVVMTodoApp/blob/master/app/src/main/java/com/plcoding/mvvmtodoapp/ui/add_edit_todo/AddEditTodoViewModel.kt
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var id = savedStateHandle.get<Int>("id") ?: -1
    var title by mutableStateOf("")
    var company by mutableStateOf("")
    var date by mutableStateOf(Date())
    var start by mutableStateOf(Date())
    var end by mutableStateOf(Date())
    var lunch_held by mutableStateOf(true)
    var lunch_start by mutableStateOf(Date())
    var lunch_end by mutableStateOf(Date())
    var paid by mutableStateOf(0.0)
    var hourly_rate by mutableStateOf("")
    var hours by mutableStateOf(0.0)
    var salary_period_month by mutableStateOf(Date().AsMonth())
    var salary_period_year by mutableStateOf(Date().AsYear())

    var months: List<String> = listOfMonths

    var years: List<String> = listOfYears

    init {
        getWork()
    }

    fun getWork() {
        if (id != -1) {
            val work: Work = runBlocking { workRepositoryImpl.getSpecificWork(id).first() }
            title = work.title
            company = work.company
            date = work.date
            start = work.start
            end = work.end
            lunch_held = work.lunch_held
            lunch_start = work.lunch_start
            lunch_end = work.lunch_end
            paid = work.paid
            hourly_rate = work.hourly_rate.toString()
            hours = work.hours
            salary_period_month = work.salary_period_month
            salary_period_year = work.salary_period_year

            println("GOT WORK $work")
        }
    }

    fun deleteWork()  {
        workRepositoryImpl.deleteWork(id)
    }

    fun save() {
        hours = start.getDiffInHours(end, lunch_held, lunch_start, lunch_end)
        val work = Work(
            id = id,
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

        if (work.title.isEmpty() && work.company.isNotEmpty()) {
            work.title = "Shift at ${work.company}"
        } else if (work.title.isEmpty() && work.company.isEmpty()) {
            work.title = "Shift at ${work.date.AsDate()}"
        }

        workRepositoryImpl.addWork(work)
        println("ADDED WORK $work")
    }


    // SOURCE: https://proandroiddev.com/the-big-form-with-jetpack-compose-7bec9cde157e
    fun showDatePickerDialog(context: Context) {
        val calendar = Calendar.getInstance()
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

    private fun getPickedTimeAsTime(hour: Int, minute: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.time
    }

    private fun getPickedDateAsDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar.time
    }

}