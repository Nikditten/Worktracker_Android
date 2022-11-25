package dtu.amd.worktracker.util

import java.util.*

object DATES {
    // get current year
    private val currentYear: Int = Date().AsYear()

    // list of months as strings based on locale
    var listOfMonths: List<String> = listOf(
        1.getMonthName(),
        2.getMonthName(),
        3.getMonthName(),
        4.getMonthName(),
        5.getMonthName(),
        6.getMonthName(),
        7.getMonthName(),
        8.getMonthName(),
        9.getMonthName(),
        10.getMonthName(),
        11.getMonthName(),
        12.getMonthName()
    )

    // list of years as strings
    var listOfYears: List<String> = listOf(
        (currentYear - 5).toString(),
        (currentYear - 4).toString(),
        (currentYear - 3).toString(),
        (currentYear - 2).toString(),
        (currentYear - 1).toString(),
        (currentYear).toString(),
        (currentYear + 1).toString(),
        (currentYear + 2).toString(),
        (currentYear + 3).toString(),
        (currentYear + 4).toString(),
        (currentYear + 5).toString()
    )
}