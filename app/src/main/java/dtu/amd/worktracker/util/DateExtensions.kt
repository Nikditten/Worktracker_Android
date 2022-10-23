package dtu.amd.worktracker.util

import java.text.SimpleDateFormat
import java.util.*

private var dateFormat = "dd-MM-yyyy"
private var timeFormat = "HH:mm"

fun Date.AsDate(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dateFormat = SimpleDateFormat(dateFormat)
    return dateFormat.format(calendar.time)
}

fun Date.AsTime(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dateFormat = SimpleDateFormat(timeFormat)
    return dateFormat.format(calendar.time)
}