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

    override fun getSpecificWork(id: Int): Work? {
        var work: Work? = null
        println("Searching for work with id: $id")
        applicationIoScope.launch {
            work = workDao.getWork(id)
        }
        println("Found work: ${work?.id}")
        return work
    }

    override fun deleteWork(id: Int) {
        applicationIoScope.launch {
            workDao.deleteWork(id)
        }
    }

    override fun getAllWork(): Flow<List<Work>> {
        return workDao.getAllWork()
    }

    override fun getEarningsByMonth(year: Int, month: Int): Double {
        var earnings: Double = 0.0
        applicationIoScope.launch {
            earnings = workDao.getEarningsByMonth(year, month)
        }
        return earnings
    }

    override fun getHoursByMonth(year: Int, month: Int): Double {
        var hours: Double = 0.0
        applicationIoScope.launch {
            hours = workDao.getHoursByMonth(year, month)
        }
        return hours
    }

    override fun getShiftsByMonth(year: Int, month: Int): Int {
        var shifts: Int = 0
        applicationIoScope.launch {
            shifts = workDao.getShiftsByMonth(year, month)
        }
        return shifts
    }

    override fun getEarningsByYear(year: Int): Double {
        var earnings: Double = 0.0
        applicationIoScope.launch {
            earnings = workDao.getEarningsByYear(year)
        }
        return earnings
    }

    override fun getHoursByYear(year: Int): Double {
        var hours: Double = 0.0
        applicationIoScope.launch {
            hours = workDao.getHoursByYear(year)
        }
        return hours
    }

    override fun getShiftsByYear(year: Int): Int {
        var shifts: Int = 0
        applicationIoScope.launch {
            shifts = workDao.getShiftsByYear(year)
        }
        return shifts
    }

}