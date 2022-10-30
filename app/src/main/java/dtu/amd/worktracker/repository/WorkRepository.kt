package dtu.amd.worktracker.dal

import dtu.amd.worktracker.dal.dao.WorkDao
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.di.ApplicationIoScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

class WorkRepository @Inject internal constructor(
    private val workDao: WorkDao,
    @ApplicationIoScope private val applicationIoScope: CoroutineScope
) {

    fun addWork(work: Work) {
        applicationIoScope.launch {
            workDao.addWork(work)
        }
    }

    fun getSpecificWork(id: Int): List<Work> {
        return workDao.getWork(id)
    }

    fun deleteWork(id: Int) {
        applicationIoScope.launch {
            workDao.deleteWork(id)
        }
    }

    fun getAllWork(): List<Work> {
        return workDao.getAllWork()
    }

}