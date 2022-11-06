package dtu.amd.worktracker.repository

import dtu.amd.worktracker.dal.model.Work
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    fun addWork(work: Work)
    fun getSpecificWork(id: Int): Work?
    fun deleteWork(id: Int)
    fun getAllWork(): Flow<List<Work>>
    fun getEarningsByMonth(year: Int, month: Int): Double
    fun getHoursByMonth(year: Int, month: Int): Double
    fun getEarningsByYear(year: Int): Double
    fun getHoursByYear(year: Int): Double
}