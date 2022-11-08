package dtu.amd.worktracker.viewmodel

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workRepositoryImpl: WorkRepositoryImpl,
    private val repo: DataStoreRepository
) : ViewModel() {

    var months: List<String> = DATES.listOfMonths

    var years: List<String> = DATES.listOfYears

    var currentMonth: Int = Date().AsMonth()
    var currentYear: Int = Date().AsYear()

    var selectedMonth: Int = currentMonth
    var selectedYear: Int = currentYear

    var monthlyPeriod: Boolean = true

    init {
        getCurrentSalaryPeriod()
    }

    fun getCurrentSalaryPeriod() = runBlocking {
        val period = repo.getSalaryPeriod()
        currentMonth = period[1]
        currentYear = period[0]
        selectedMonth = currentMonth
        selectedYear = currentYear
    }

    fun getEarnings(): Double {
        if (monthlyPeriod) {
            return workRepositoryImpl.getEarningsByMonth(selectedYear, selectedMonth)
        } else {
            return workRepositoryImpl.getEarningsByYear(selectedYear)
        }
    }


    fun getExpectedPayout(): Double {
        if (monthlyPeriod) {
            return 0.0
        } else {
            return 0.0
        }
    }

    fun getHours(): Double {
        if (monthlyPeriod) {
            return workRepositoryImpl.getHoursByMonth(selectedYear, selectedMonth)
        } else {
            return workRepositoryImpl.getHoursByYear(selectedYear)
        }
    }

    fun getShifts(): Int {
        if (monthlyPeriod) {
            return workRepositoryImpl.getShiftsByMonth(selectedYear, selectedMonth)
        } else {
            return workRepositoryImpl.getShiftsByYear(selectedYear)
        }
    }

}