package dtu.amd.worktracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.util.PREF_KEYS.APRIL
import dtu.amd.worktracker.util.PREF_KEYS.AUGUST
import dtu.amd.worktracker.util.PREF_KEYS.DECEMBER
import dtu.amd.worktracker.util.PREF_KEYS.FEBRUARY
import dtu.amd.worktracker.util.PREF_KEYS.JANUARY
import dtu.amd.worktracker.util.PREF_KEYS.JULY
import dtu.amd.worktracker.util.PREF_KEYS.JUNE
import dtu.amd.worktracker.util.PREF_KEYS.MARCH
import dtu.amd.worktracker.util.PREF_KEYS.MAY
import dtu.amd.worktracker.util.PREF_KEYS.NOVEMBER
import dtu.amd.worktracker.util.PREF_KEYS.OCTOBER
import dtu.amd.worktracker.util.PREF_KEYS.SALARY
import dtu.amd.worktracker.util.PREF_KEYS.SEPTEMBER
import dtu.amd.worktracker.util.PREF_KEYS.TAX_ADDITIONAL
import dtu.amd.worktracker.util.PREF_KEYS.TAX_DEDUCTION
import dtu.amd.worktracker.util.PREF_KEYS.TAX_PERCENTAGE
import javax.inject.Inject

class SettingsViewModel(): ViewModel() {

    var salary by mutableStateOf(0.0)

    var tax_deduction by mutableStateOf(0.0)
    var tax_percentage by mutableStateOf(0.0)
    var tax_additional by mutableStateOf(0.0)

    var january by mutableStateOf(0)
    var february by mutableStateOf(0)
    var marts by mutableStateOf(0)
    var april by mutableStateOf(0)
    var may by mutableStateOf(0)
    var june by mutableStateOf(0)
    var july by mutableStateOf(0)
    var august by mutableStateOf(0)
    var september by mutableStateOf(0)
    var october by mutableStateOf(0)
    var november by mutableStateOf(0)
    var december by mutableStateOf(0)

//    init {
//        salary = repo.getDouble(SALARY)
//        tax_deduction = repo.getDouble(TAX_DEDUCTION)
//        tax_percentage = repo.getDouble(TAX_PERCENTAGE)
//        tax_additional = repo.getDouble(TAX_ADDITIONAL)
//        january = repo.getInt(JANUARY)
//        february = repo.getInt(FEBRUARY)
//        marts = repo.getInt(MARCH)
//        april = repo.getInt(APRIL)
//        may = repo.getInt(MAY)
//        june = repo.getInt(JUNE)
//        july = repo.getInt(JULY)
//        august = repo.getInt(AUGUST)
//        september = repo.getInt(SEPTEMBER)
//        october = repo.getInt(OCTOBER)
//        november = repo.getInt(NOVEMBER)
//        december = repo.getInt(DECEMBER)
//    }
//
//    fun save() {
//        repo.setDouble(SALARY, salary)
//
//        repo.setDouble(TAX_DEDUCTION, tax_deduction)
//        repo.setDouble(TAX_PERCENTAGE, tax_percentage)
//        repo.setDouble(TAX_ADDITIONAL, tax_additional)
//
//        repo.setInt(JANUARY, january)
//        repo.setInt(FEBRUARY, february)
//        repo.setInt(MARCH, marts)
//        repo.setInt(APRIL, april)
//        repo.setInt(MAY, may)
//        repo.setInt(JUNE, june)
//        repo.setInt(JULY, july)
//        repo.setInt(AUGUST, august)
//        repo.setInt(SEPTEMBER, september)
//        repo.setInt(OCTOBER, october)
//        repo.setInt(NOVEMBER, november)
//        repo.setInt(DECEMBER, december)
//    }

}