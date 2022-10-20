package dtu.amd.worktracker

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
}