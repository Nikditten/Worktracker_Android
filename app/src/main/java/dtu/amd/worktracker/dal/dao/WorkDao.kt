package dtu.amd.worktracker.dal.dao

import androidx.room.Insert
import androidx.room.Query
import dtu.amd.worktracker.dal.model.Work

interface WorkDao {

    @Insert
    fun addWork(work: Work)

    @Query("SELECT * FROM work WHERE id = :id")
    fun getWork(id: Int): List<Work>

    @Query("DELETE FROM work WHERE id = :id")
    fun deleteWork(id: Int)

    @Query("SELECT * FROM work")
    fun getAllWork(): List<Work>

}