package dtu.amd.worktracker.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private var dateFormat = "dd-MM-yyyy"
private var timeFormat = "HH:mm"
private var monthFormat = "MMMM"

// Format date to string
fun Date.AsDate(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dateFormat = SimpleDateFormat(dateFormat)
    return dateFormat.format(calendar.time)
}

// Format time to string
fun Date.AsTime(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dateFormat = SimpleDateFormat(timeFormat)
    return dateFormat.format(calendar.time)
}

// Get difference between two dates in in hours
fun Date.getDiffInHours(end: Date, lunch: Boolean = false, lunch_start: Date, lunch_end: Date): Double {
    // SOURCE: https://stackoverflow.com/questions/17821601/set-time-to-000000

    // Set seconds and milliseconds to 0 for a more accurate calculation
    val start_cal = Calendar.getInstance()
    start_cal.setTime(this)
    start_cal[Calendar.MILLISECOND] = 0
    start_cal[Calendar.SECOND] = 0

    // Set seconds and milliseconds to 0 for a more accurate calculation
    val end_cal = Calendar.getInstance()
    end_cal.setTime(end)
    end_cal[Calendar.MILLISECOND] = 0
    end_cal[Calendar.SECOND] = 0

    // Set seconds and milliseconds to 0 for a more accurate calculation
    val lunch_start_cal = Calendar.getInstance()
    lunch_start_cal.setTime(lunch_start)
    lunch_start_cal[Calendar.MILLISECOND] = 0
    lunch_start_cal[Calendar.SECOND] = 0

    // Set seconds and milliseconds to 0 for a more accurate calculation
    val lunch_end_cal = Calendar.getInstance()
    lunch_end_cal.setTime(lunch_end)
    lunch_end_cal[Calendar.MILLISECOND] = 0
    lunch_end_cal[Calendar.SECOND] = 0

    // Calculate difference in milliseconds
    val diff = end_cal.timeInMillis - start_cal.timeInMillis
    // Calculate difference in hours
    var total = diff / (60 * 60 * 1000.0)
    // Is lunch is held then subtract lunch time from total
    if (lunch) {
        val lunchDiff = lunch_end_cal.timeInMillis - lunch_start_cal.timeInMillis
        val lunchTotal = lunchDiff / (60 * 60 * 1000.0)
        total -= lunchTotal
    }

    // Round to 2 decimals and return
    return total.RoundTo2Decimals()
}

// Get year from current date
fun Date.AsYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

// Get month from current date
fun Date.AsMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MONTH) + 1
}

// Get day from current date
fun Date.AsDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}