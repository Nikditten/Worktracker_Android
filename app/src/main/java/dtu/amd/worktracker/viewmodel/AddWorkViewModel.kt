package dtu.amd.worktracker.viewmodel

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import dtu.amd.worktracker.model.Work
import dtu.amd.worktracker.preview.data.Workitems
import dtu.amd.worktracker.util.AsDate
import dtu.amd.worktracker.util.AsTime
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class AddWorkViewModel() {

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
    var salary_period_month by mutableStateOf(1)
    var salary_period_year by mutableStateOf(2022)


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

}