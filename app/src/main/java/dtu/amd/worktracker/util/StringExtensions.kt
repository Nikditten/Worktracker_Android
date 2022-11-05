package dtu.amd.worktracker.util

import java.text.SimpleDateFormat
import java.util.*

private var monthFormat = "MMMM"

fun Int.getMonthName(): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, this)
    val dateFormat = SimpleDateFormat(monthFormat)
    return dateFormat.format(calendar.time)
}