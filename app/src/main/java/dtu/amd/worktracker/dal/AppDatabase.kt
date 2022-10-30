package dtu.amd.worktracker.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dtu.amd.worktracker.dal.dao.WorkDao
import dtu.amd.worktracker.dal.model.Work

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Database(
    entities = [Work::class],
    autoMigrations = [
    ],
    exportSchema = true, version = 1)

@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workDao(): WorkDao
}