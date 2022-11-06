package dtu.amd.worktracker.util

import kotlin.math.roundToInt

fun Double.RoundTo2Decimals(): Double {
    return (this * 100.0).roundToInt() / 100.0
}