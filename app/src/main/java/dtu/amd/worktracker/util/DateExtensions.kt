package dtu.amd.worktracker.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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
    // SOURCE: https://stackoverflow.com/questions/17821601/set-time-to-000000
    val start_cal = Calendar.getInstance()
    start_cal.setTime(this)
    start_cal[Calendar.MILLISECOND] = 0
    start_cal[Calendar.SECOND] = 0

    val end_cal = Calendar.getInstance()
    end_cal.setTime(end)
    end_cal[Calendar.MILLISECOND] = 0
    end_cal[Calendar.SECOND] = 0

    val lunch_start_cal = Calendar.getInstance()
    lunch_start_cal.setTime(lunch_start)
    lunch_start_cal[Calendar.MILLISECOND] = 0
    lunch_start_cal[Calendar.SECOND] = 0

    val lunch_end_cal = Calendar.getInstance()
    lunch_end_cal.setTime(lunch_end)
    lunch_end_cal[Calendar.MILLISECOND] = 0
    lunch_end_cal[Calendar.SECOND] = 0

    val diff = end_cal.timeInMillis - start_cal.timeInMillis
    println("diff: $diff")
    var total = diff / (60 * 60 * 1000.0)
    if (lunch) {
        val lunchDiff = lunch_end_cal.timeInMillis - lunch_start_cal.timeInMillis
        val lunchTotal = lunchDiff / (60 * 60 * 1000.0)
        total -= lunchTotal
    }

    return total.RoundTo2Decimals()
}

fun Date.AsYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.AsMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MONTH) + 1
}

fun Date.AsDay(): Int {
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