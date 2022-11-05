package dtu.amd.worktracker.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepository
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.util.asMonth
import dtu.amd.worktracker.util.asYear
import dtu.amd.worktracker.util.getMonthName
import java.util.*
import javax.inject.Inject


@HiltViewModel
class EditViewModel @Inject constructor(
    workRepository: WorkRepository,
    // SOURCE: https://github.com/philipplackner/MVVMTodoApp/blob/master/app/src/main/java/com/plcoding/mvvmtodoapp/ui/add_edit_todo/AddEditTodoViewModel.kt
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var hourly_paid by mutableStateOf(true)

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
    var one_time_fee by mutableStateOf(0.0)
    var hours by mutableStateOf(0.0)
    var salary_period_month by mutableStateOf(Date().asMonth())
    var salary_period_year by mutableStateOf(Date().asYear())

    private val currentYear: Int = Date().asYear()

    var months: List<String> = listOf(
        1.getMonthName(),
        2.getMonthName(),
        3.getMonthName(),
        4.getMonthName(),
        5.getMonthName(),
        6.getMonthName(),
        7.getMonthName(),
        8.getMonthName(),
        9.getMonthName(),
        10.getMonthName(),
        11.getMonthName(),
        12.getMonthName()
    )
    var years: List<String> = listOf(
        (currentYear - 5).toString(),
        (currentYear - 4).toString(),
        (currentYear - 3).toString(),
        (currentYear - 2).toString(),
        (currentYear - 1).toString(),
        (currentYear).toString(),
        (currentYear + 1).toString(),
        (currentYear + 2).toString(),
        (currentYear + 3).toString(),
        (currentYear + 4).toString(),
        (currentYear + 5).toString()
    )

    init {
        if (id != -1) {
            val work = workRepository.getSpecificWork(id)
            println("workId from repo: ${work?.id}")
            if (work != null) {
                title = work.title
                company = work.company
                date = work.date
                start = work.start
                end = work.end
                lunch_held = work.lunch_held
                lunch_start = work.lunch_start
                lunch_end = work.lunch_end
                paid = work.paid
                one_time_fee = work.one_time_fee
                hours = work.hours
                salary_period_month = work.salary_period_month
                salary_period_year = work.salary_period_year
            }
        }
    }

    fun save() {
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
            paid = paid,
            one_time_fee = one_time_fee,
            hours = hours,
            salary_period_month = salary_period_month,
            salary_period_year = salary_period_year
        )
        //workRepository.addWork(work)
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