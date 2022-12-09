package dtu.amd.worktracker.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepositoryImpl
import dtu.amd.worktracker.dal.model.Work
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


// A Hilt View Model is a Jetpack ViewModel that is constructor injected by Hilt.
// To enable injection of a ViewModel by Hilt use the @HiltViewModel annotation
@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
): ViewModel() {

    // Get all work from the database as a flow of list of work
    fun getAllWork(): Flow<List<Work>> {
        return workRepositoryImpl.getAllWork()
    }

}