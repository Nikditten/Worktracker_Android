package dtu.amd.worktracker.dal.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import java.util.*

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "work")
data class Work(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    val company: String,
    @ColumnInfo(name = "date")
    var date: Date,
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