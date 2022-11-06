package dtu.amd.worktracker.util

import java.text.SimpleDateFormat
import java.util.*

private var dateFormat = "dd-MM-yyyy"
private var timeFormat = "HH:mm"
private var monthFormat = "MMMM"

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

fun Date.getDiffInHours(end: Date): Double {
    val diff = end.time - this.time
    println("DATEDIFF: ${diff / (60 * 60 * 1000.0)}")
    return (diff / (60 * 60 * 1000.0)).RoundTo2Decimals()
}

fun Date.asYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    println("YEAR: ${calendar.get(Calendar.YEAR)}")
    return calendar.get(Calendar.YEAR)
}

fun Date.asMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    println("MONTH: ${calendar.get(Calendar.MONTH)}")
    return calendar.get(Calendar.MONTH)
}

fun Date.asMonthName(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dateFormat = SimpleDateFormat(monthFormat)
    return dateFormat.format(calendar.time)
}