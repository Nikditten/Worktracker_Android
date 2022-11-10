package dtu.amd.worktracker.repository

import dtu.amd.worktracker.dal.model.Work
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    fun addWork(work: Work)
    fun getSpecificWork(id: Int): Flow<Work>
    fun deleteWork(id: Int)
    fun getAllWork(): Flow<List<Work>>
    fun getEarningsByMonth(year: Int, month: Int): Flow<Double?>
    fun getHoursByMonth(year: Int, month: Int): Flow<Double?>
    fun getShiftsByMonth(year: Int, month: Int): Flow<Int?>
    fun getEarningsByYear(year: Int): Flow<Double?>
    fun getHoursByYear(year: Int): Flow<Double?>
    fun getShiftsByYear(year: Int): Flow<Int?>
}