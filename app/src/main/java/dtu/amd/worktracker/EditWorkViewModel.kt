package dtu.amd.worktracker

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
import java.text.SimpleDateFormat
import java.util.*

class EditWorkViewModel(id: Int) {

    private val work: Work = Workitems().getByID(id = id)

    var title by mutableStateOf(work.title)
    var company by mutableStateOf(work.company)
    var date by mutableStateOf(work.date)
    var start by mutableStateOf(work.start)
    var end by mutableStateOf(work.end)
    var lunch_held by mutableStateOf(work.lunch_held)
    var lunch_start by mutableStateOf(work.lunch_start)
    var lunch_end by mutableStateOf(work.lunch_end)
    var hourly_paid by mutableStateOf(work.hourly_paid)
    var paid by mutableStateOf(work.paid)
    var salary_period_month by mutableStateOf(work.salary_period_month)
    var salary_period_year by mutableStateOf(work.salary_period_year)


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
                        calendar.time = end
                    }
                    "lunch_start" -> {
                        calendar.time = lunch_start
                    }
                    "lunch_end" -> {
                        calendar.time = lunch_end
                    }
                }

            }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true
        ).show()
    }

    private fun getPickedTimeAsTime(hour: Int, minute: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(hour, minute)
        return calendar.time
    }

    private fun getPickedDateAsDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return calendar.time
    }

}