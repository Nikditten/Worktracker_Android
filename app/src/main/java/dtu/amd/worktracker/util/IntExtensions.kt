package dtu.amd.worktracker.util

import java.text.SimpleDateFormat
import java.util.*

private var monthFormat = "MMMM"

// Get month name based on month number and locale
fun Int.getMonthName(): String {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, this-1)
    val dateFormat = SimpleDateFormat(monthFormat)
    return dateFormat.format(calendar.time)
}