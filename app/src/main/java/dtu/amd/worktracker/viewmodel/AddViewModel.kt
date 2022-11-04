package dtu.amd.worktracker.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dtu.amd.worktracker.dal.WorkRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.util.asMonth
import dtu.amd.worktracker.util.asYear
import dtu.amd.worktracker.util.getDiffInHours
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
private val workRepository: WorkRepository,
): ViewModel() {

    var title by mutableStateOf("")
    var company by mutableStateOf("")
    var date by mutableStateOf(Date())
    var start by mutableStateOf(Date())
    var end by mutableStateOf(Date())
    var lunch_held by mutableStateOf(true)
    var lunch_start by mutableStateOf(Date())
    var lunch_end by mutableStateOf(Date())
    var hourly_paid by mutableStateOf(true)
    var paid by mutableStateOf(0.0)
    var one_time_fee by mutableStateOf(0.0)
    var salary_period_month by mutableStateOf(Date().asMonth())
    var salary_period_year by mutableStateOf(Date().asYear())

    var months: List<String> = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    var years: List<String> = listOf("2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030")

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
            {_, hour, minute ->

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

    fun save() {
        workRepository.addWork(
            Work(
                id = 0,
                title = title,
                company = company,
                date = date,
                start = start,
                end = end,
                lunch_held = lunch_held,
                lunch_start = lunch_start,
                lunch_end = lunch_end,
                paid = paid,
                one_time_fee = one_time_fee,
                hours = start.getDiffInHours(end),
                salary_period_month = salary_period_month,
                salary_period_year = salary_period_year
            )
        )
    }

}