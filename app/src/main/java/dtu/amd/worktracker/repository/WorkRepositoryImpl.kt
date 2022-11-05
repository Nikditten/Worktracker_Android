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
    @ApplicationIoScope private val applicationIoScope: CoroutineScope
): WorkRepository {

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

}