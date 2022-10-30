package dtu.amd.worktracker.dal

import androidx.room.TypeConverter
import java.util.*

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

// SOURCE: https://stackoverflow.com/a/47954610

object TypeConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}