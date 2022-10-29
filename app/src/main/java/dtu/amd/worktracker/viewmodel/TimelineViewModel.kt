package dtu.amd.worktracker.viewmodel

import dtu.amd.worktracker.dal.WorkRepository
import javax.inject.Inject

class TimelineViewModel {

    @Inject
    lateinit var workRepository: WorkRepository

    var workList = workRepository.getAllWork()

}