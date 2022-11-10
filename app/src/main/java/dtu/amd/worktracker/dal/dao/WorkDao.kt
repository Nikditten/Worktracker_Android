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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWork(work: Work)

    @Query("SELECT * FROM work WHERE id = :id")
    fun getWork(id: Int): Flow<Work>

    @Query("DELETE FROM work WHERE id = :id")
    fun deleteWork(id: Int)

    @Query("SELECT * FROM work")
    fun getAllWork(): Flow<List<Work>>

    @Query("SELECT SUM(paid) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getEarningsByMonth(year: Int, month: Int): Flow<Double>

    @Query("SELECT SUM(hours) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getHoursByMonth(year: Int, month: Int): Flow<Double>

    @Query("SELECT COUNT(*) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getShiftsByMonth(year: Int, month: Int): Flow<Int>

    @Query("SELECT SUM(paid) FROM work WHERE salary_period_year = :year")
    fun getEarningsByYear(year: Int): Flow<Double>

    @Query("SELECT SUM(hours) FROM work WHERE salary_period_year = :year")
    fun getHoursByYear(year: Int): Flow<Double>

    @Query("SELECT COUNT(*) FROM work WHERE salary_period_year = :year")
    fun getShiftsByYear(year: Int): Flow<Int>

}