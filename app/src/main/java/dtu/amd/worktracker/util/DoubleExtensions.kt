package dtu.amd.worktracker.util

import kotlin.math.roundToInt

// Extension function to round a double to 2 decimals
fun Double.RoundTo2Decimals(): Double {
    return (this * 100.0).roundToInt() / 100.0
}