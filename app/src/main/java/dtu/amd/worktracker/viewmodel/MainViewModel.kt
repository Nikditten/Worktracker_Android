package dtu.amd.worktracker.viewmodel

import dtu.amd.worktracker.dal.WorkRepository
import javax.inject.Inject

class MainViewModel {

    @Inject
    lateinit var workRepository: WorkRepository

    var workList = workRepository.getAllWork()

}