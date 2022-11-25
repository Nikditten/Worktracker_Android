package dtu.amd.worktracker.dal

import dtu.amd.worktracker.dal.dao.WorkDao
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.di.ApplicationIoScope
import dtu.amd.worktracker.repository.WorkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Singleton
class WorkRepositoryImpl @Inject internal constructor(
    private val workDao: WorkDao,
    @ApplicationIoScope private val applicationIoScope: CoroutineScope,
) : WorkRepository {

    // Add a work to the database
    override fun addWork(work: Work) {
        applicationIoScope.launch {
            workDao.addWork(work)
        }
    }

    // Get work from the database by id
    override fun getSpecificWork(id: Int): Flow<Work> {
        return workDao.getWork(id)
    }

    // Delete work from the database by id
    override fun deleteWork(id: Int) {
        applicationIoScope.launch {
            workDao.deleteWork(id)
        }
    }

    // Get all work from the database
    override fun getAllWork(): Flow<List<Work>> {
        return workDao.getAllWork()
    }

    // Get earnings by month and year from the database
    override fun getEarningsByMonth(year: Int, month: Int): Flow<Double?> {
        return workDao.getEarningsByMonth(year, month)
    }

    // Get hours by year from the database
    override fun getHoursByMonth(year: Int, month: Int): Flow<Double?> {
        return workDao.getHoursByMonth(year, month)
    }

    // Get number of shifts by year from the database
    override fun getShiftsByMonth(year: Int, month: Int): Flow<Int?> {
        return workDao.getShiftsByMonth(year, month)
    }

    // Get earnings by year from the database
    override fun getEarningsByYear(year: Int): Flow<Double?> {
        return workDao.getEarningsByYear(year)
    }

    // Get hours by year from the database
    override fun getHoursByYear(year: Int): Flow<Double?> {
        return workDao.getHoursByYear(year)
    }

    // Get number of shifts by year from the database
    override fun getShiftsByYear(year: Int): Flow<Int?> {
        return workDao.getShiftsByYear(year)
    }

}