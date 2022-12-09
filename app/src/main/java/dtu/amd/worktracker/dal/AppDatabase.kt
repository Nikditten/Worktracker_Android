package dtu.amd.worktracker.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dtu.amd.worktracker.dal.dao.WorkDao
import dtu.amd.worktracker.dal.model.Work

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

// Init database with one table (Work) and version 1
@Database(
    entities = [Work::class],
    autoMigrations = [ // her kan man f.eks. skrive at den skal automigrere
                     // from version 1 til 2
    ],
    exportSchema = true, version = 1)

// Use TypeConverter to convert Date to Long and back again
@TypeConverters(TypeConverter::class)
// The database class is abstract and extends RoomDatabase
abstract class AppDatabase : RoomDatabase() {
    // The WorkDao is abstract and returns the WorkDao and is used to access the database
    abstract fun workDao(): WorkDao
}