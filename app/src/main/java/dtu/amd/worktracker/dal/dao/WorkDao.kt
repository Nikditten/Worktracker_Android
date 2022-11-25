package dtu.amd.worktracker.dal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dtu.amd.worktracker.dal.model.Work
import kotlinx.coroutines.flow.Flow

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Dao
interface WorkDao {

    // Insert or update a work object into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWork(work: Work)

    // Get work object by ID from the database
    @Query("SELECT * FROM work WHERE id = :id")
    fun getWork(id: Int): Flow<Work>

    // Delete work object by ID from the database
    @Query("DELETE FROM work WHERE id = :id")
    fun deleteWork(id: Int)

    // Get all work objects from the database and order them by date
    @Query("SELECT * FROM work ORDER BY date DESC")
    fun getAllWork(): Flow<List<Work>>

    // Get sum of paid column
    @Query("SELECT SUM(paid) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getEarningsByMonth(year: Int, month: Int): Flow<Double?>

    // Get sum of hours column
    @Query("SELECT SUM(hours) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getHoursByMonth(year: Int, month: Int): Flow<Double?>

    // Get count of work objects
    @Query("SELECT COUNT(*) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getShiftsByMonth(year: Int, month: Int): Flow<Int?>

    // Get sum of paid column by year
    @Query("SELECT SUM(paid) FROM work WHERE salary_period_year = :year")
    fun getEarningsByYear(year: Int): Flow<Double?>

    // Get sum of hours column by year
    @Query("SELECT SUM(hours) FROM work WHERE salary_period_year = :year")
    fun getHoursByYear(year: Int): Flow<Double?>

    // Get count of work objects by year
    @Query("SELECT COUNT(*) FROM work WHERE salary_period_year = :year")
    fun getShiftsByYear(year: Int): Flow<Int?>

}