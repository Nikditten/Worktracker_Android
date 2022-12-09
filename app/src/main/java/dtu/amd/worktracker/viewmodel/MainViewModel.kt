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
import dtu.amd.worktracker.util.PREF_KEYS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

// A Hilt View Model is a Jetpack ViewModel that is constructor injected by Hilt.
// To enable injection of a ViewModel by Hilt use the @HiltViewModel annotation
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

    // Get the salary period from the data store and set the current month and year
    // This is runned with CoroutineScope because it is a suspend function
    fun getCurrentSalaryPeriod() = runBlocking {
        val period = repo.getSalaryPeriod()
        currentMonth = period[1]
        currentYear = period[0]
        selectedMonth = currentMonth
        selectedYear = currentYear
    }

    // Get earnings for either month or year
    fun getEarnings(): Flow<Double?> {
        if (monthlyPeriod) {
            return workRepositoryImpl.getEarningsByMonth(selectedYear, selectedMonth)
        } else {
            return workRepositoryImpl.getEarningsByYear(selectedYear)
        }
    }

    // Calculate the total amount the user can expect to be paid for either month or year
    fun getExptectedEarnings(): Double {
        var earnings = 0.0
        val tax_percentage = runBlocking { repo.getDouble(PREF_KEYS.TAX_PERCENTAGE, 0.0) / 100 }
        val tax_additional = runBlocking { repo.getDouble(PREF_KEYS.TAX_ADDITIONAL, 0.0) / 100 }
        val tax_deduction = runBlocking { repo.getDouble(PREF_KEYS.TAX_DEDUCTION, 0.0) }

        if (monthlyPeriod) {
            earnings = runBlocking { workRepositoryImpl.getEarningsByMonth(selectedYear, selectedMonth).first() ?: 0.0 }
        } else {
            earnings = runBlocking { workRepositoryImpl.getEarningsByYear(selectedYear).first() ?: 0.0 }
        }

        earnings = earnings - (earnings * tax_additional)

        if (earnings > tax_deduction && tax_percentage > 0 && tax_deduction > 0) {
            return earnings - (earnings - tax_deduction) * tax_percentage
        }

        return earnings
    }

    // Get the total amount of hours worked for either month or year
    fun getHours(): Flow<Double?> {
        if (monthlyPeriod) {
            return workRepositoryImpl.getHoursByMonth(selectedYear, selectedMonth)
        } else {
            return workRepositoryImpl.getHoursByYear(selectedYear)
        }
    }

    // Get the total amount of shifts for either month or year
    fun getShifts(): Flow<Int?> {
        if (monthlyPeriod) {
            return workRepositoryImpl.getShiftsByMonth(selectedYear, selectedMonth)
        } else {
            return workRepositoryImpl.getShiftsByYear(selectedYear)
        }
    }

    // Increment the selected month and year if selected month is december then increment the year and set the month to january
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

    // Decrement the selected month and year if selected month is january then decrement the year and set the month to december
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

    // Toggle between monthly and yearly period
    fun changePeriod() {
        monthlyPeriod = !monthlyPeriod
    }

    // Reset the selected month and year to the current month and year
    fun resetPeriod() {
        selectedMonth = currentMonth
        selectedYear = currentYear
    }

}