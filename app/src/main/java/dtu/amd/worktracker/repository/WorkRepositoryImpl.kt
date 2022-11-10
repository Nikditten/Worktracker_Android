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

    override fun addWork(work: Work) {
        applicationIoScope.launch {
            workDao.addWork(work)
        }
    }

    override fun getSpecificWork(id: Int): Flow<Work> {
        return workDao.getWork(id)
    }

    override fun deleteWork(id: Int) {
        applicationIoScope.launch {
            workDao.deleteWork(id)
        }
    }

    override fun getAllWork(): Flow<List<Work>> {
        return workDao.getAllWork()
    }

    override fun getEarningsByMonth(year: Int, month: Int): Flow<Double?> {
        return workDao.getEarningsByMonth(year, month)
    }

    override fun getHoursByMonth(year: Int, month: Int): Flow<Double?> {
        return workDao.getHoursByMonth(year, month)
    }

    override fun getShiftsByMonth(year: Int, month: Int): Flow<Int?> {
        return workDao.getShiftsByMonth(year, month)
    }

    override fun getEarningsByYear(year: Int): Flow<Double?> {
        return workDao.getEarningsByYear(year)
    }

    override fun getHoursByYear(year: Int): Flow<Double?> {
        return workDao.getHoursByYear(year)
    }

    override fun getShiftsByYear(year: Int): Flow<Int?> {
        return workDao.getShiftsByYear(year)
    }

}