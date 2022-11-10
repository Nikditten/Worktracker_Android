package dtu.amd.worktracker.viewmodel

import androidx.compose.runtime.collectAsState
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

    var monthlyPeriod: Boolean by mutableStateOf(true)

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

    fun getEarnings(): Flow<Double?> {
        return workRepositoryImpl.getEarningsByMonth(selectedMonth, selectedYear)
    }

    fun getHours(): Flow<Double?> {
        return workRepositoryImpl.getHoursByMonth(selectedYear, selectedMonth)
    }

    fun getShifts(): Flow<Int?> {
        return workRepositoryImpl.getShiftsByMonth(selectedYear, selectedMonth)
    }

    fun incrementPeriod() {
        if (monthlyPeriod) {
            if (selectedMonth == 12) {
                selectedMonth = 1
                selectedYear++
            } else {
                selectedMonth++
            }
        } else {
            selectedYear++
        }
    }

    fun decrementPeriod() {
        if (monthlyPeriod) {
            if (selectedMonth == 1) {
                selectedMonth = 12
                selectedYear--
            } else {
                selectedMonth--
            }
        } else {
            selectedYear--
        }
    }

    fun changePeriod() {
        monthlyPeriod = !monthlyPeriod
    }

    fun resetPeriod() {
        selectedMonth = currentMonth
        selectedYear = currentYear
    }

}