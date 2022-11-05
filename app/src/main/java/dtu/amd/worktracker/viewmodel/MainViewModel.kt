package dtu.amd.worktracker.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepositoryImpl
import dtu.amd.worktracker.dal.model.Work
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
): ViewModel() {

        fun getAllWork(): Flow<List<Work>> {
            return workRepositoryImpl.getAllWork()
        }

}