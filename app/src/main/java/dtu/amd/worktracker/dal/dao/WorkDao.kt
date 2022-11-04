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

    @Insert()
    fun addWork(work: Work)

    @Query("SELECT * FROM work WHERE id = :id")
    fun getWork(id: Int): Work?

    @Query("DELETE FROM work WHERE id = :id")
    fun deleteWork(id: Int)

    @Query("SELECT * FROM work")
    fun getAllWork(): Flow<List<Work>>

    @Query("SELECT SUM(:type) FROM work WHERE salary_period_month = :month AND salary_period_year = :year")
    fun getSum(type: String, year: Int, month: Int): Double
}