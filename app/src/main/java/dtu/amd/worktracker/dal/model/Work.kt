package dtu.amd.worktracker.dal.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import java.util.*

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dtu.amd.worktracker.dal.TypeConverter

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Entity(tableName = "work")
data class Work(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    val company: String,
    @TypeConverters(TypeConverter::class)
    @ColumnInfo(name = "date")
    var date: Date,
    @TypeConverters(TypeConverter::class)
    val start: Date,
    @TypeConverters(TypeConverter::class)
    val end: Date,
    val lunch_held: Boolean,
    @TypeConverters(TypeConverter::class)
    val lunch_start: Date,
    @TypeConverters(TypeConverter::class)
    val lunch_end: Date,
    val hourly_paid: Boolean,
    val paid: Double,
    val salary_period_month: Int,
    val salary_period_year: Int
)