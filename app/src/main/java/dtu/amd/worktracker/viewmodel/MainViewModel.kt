package dtu.amd.worktracker.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepository
import dtu.amd.worktracker.dal.model.Work
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workRepository: WorkRepository,
): ViewModel() {

        fun getAllWork(): Flow<List<Work>> {
            return workRepository.getAllWork()
        }

}