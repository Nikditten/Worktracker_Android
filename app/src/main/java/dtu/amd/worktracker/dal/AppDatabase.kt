package dtu.amd.worktracker.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import dtu.amd.worktracker.dal.dao.WorkDao
import dtu.amd.worktracker.dal.model.Work

@Database(
    entities = [Work::class],
    autoMigrations = [
    ],
    exportSchema = true, version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun workDao(): WorkDao
}