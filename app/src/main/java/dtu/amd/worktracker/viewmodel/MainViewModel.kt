package dtu.amd.worktracker.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepositoryImpl
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.util.asMonth
import dtu.amd.worktracker.util.asYear
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
) : ViewModel() {

    var currentMonth: Int = Date().asMonth()
    var currentYear: Int = Date().asYear()

    var selectedMonth: Int = currentMonth
    var selectedYear: Int = currentYear

    fun getEarningsByMonth(): Double {
        return workRepositoryImpl.getEarningsByMonth(selectedMonth, selectedYear)
    }

}