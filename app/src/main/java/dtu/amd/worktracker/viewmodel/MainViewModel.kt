package dtu.amd.worktracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.dal.WorkRepositoryImpl
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.repository.DataStoreRepository
import dtu.amd.worktracker.util.AsMonth
import dtu.amd.worktracker.util.AsYear
import dtu.amd.worktracker.util.DATES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
    private val repo: DataStoreRepository
) : ViewModel() {


    var currentMonth: Int = Date().AsMonth()
    var currentYear: Int = Date().AsYear()

    var selectedMonth: Int by mutableStateOf(currentMonth)
    var selectedYear: Int by mutableStateOf(currentYear)

    var monthlyPeriod: Boolean = true

    var earnings: Double by mutableStateOf(0.0)
    var expectedIncome: Double = 0.0
    var hours: Double by mutableStateOf(0.0)
    var shifts: Int by mutableStateOf(0)

    init {
        getCurrentSalaryPeriod()
        getStats()
    }

    fun getCurrentSalaryPeriod() = runBlocking {
        val period = repo.getSalaryPeriod()
        currentMonth = period[1]
        currentYear = period[0]
        selectedMonth = currentMonth
        selectedYear = currentYear
    }

    fun getStats() = runBlocking {

        if (monthlyPeriod) {
            earnings = workRepositoryImpl.getEarningsByMonth(selectedYear, selectedMonth).first()
            expectedIncome = 0.0
            hours = workRepositoryImpl.getHoursByMonth(selectedYear, selectedMonth).first() ?: 0.0
            shifts = workRepositoryImpl.getShiftsByMonth(selectedYear, selectedMonth).first() ?: 0
        } else {
            earnings = workRepositoryImpl.getEarningsByYear(selectedYear).first() ?: 0.0
            expectedIncome = 0.0
            hours = workRepositoryImpl.getHoursByYear(selectedYear).first() ?: 0.0
            shifts = workRepositoryImpl.getShiftsByYear(selectedYear).first() ?: 0
        }

    }

}