package dtu.amd.worktracker.model

import java.util.*

data class Work(
    val id: Int,
    var title: String,
    val company: String,
    val date: Date,
    val start: Date,
    val end: Date,
    val lunch_held: Boolean,
    val lunch_start: Date,
    val lunch_end: Date,
    val hourly_paid: Boolean,
    val paid: Double,
    val salary_period_month: Int,
    val salary_period_year: Int
)