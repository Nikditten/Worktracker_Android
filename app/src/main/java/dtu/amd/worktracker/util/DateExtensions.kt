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

fun Date.getDiffInHours(end: Date, lunch: Boolean = false, lunch_start: Date, lunch_end: Date): Double {
    val diff = end.time - this.time
    var total = (diff / (60 * 60 * 1000.0)).RoundTo2Decimals()
    if (lunch) {
        val lunchDiff = lunch_end.time - lunch_start.time
        val lunchTotal = (lunchDiff / (60 * 60 * 1000.0)).RoundTo2Decimals()
        total -= lunchTotal
    }
    return total
}

fun Date.asYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.asMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MONTH) + 1
}

fun Date.asDate(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.asMonthName(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dateFormat = SimpleDateFormat(monthFormat)
    return dateFormat.format(calendar.time)
}