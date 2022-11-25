package dtu.amd.worktracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dtu.amd.worktracker.repository.DataStoreRepository
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
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: DataStoreRepository
):ViewModel() {

    // Variable for the input fields
    var salary by mutableStateOf("")

    var tax_deduction by mutableStateOf("")
    var tax_percentage by mutableStateOf("")
    var tax_additional by mutableStateOf("")

    var january by mutableStateOf("")
    var february by mutableStateOf("")
    var marts by mutableStateOf("")
    var april by mutableStateOf("")
    var may by mutableStateOf("")
    var june by mutableStateOf("")
    var july by mutableStateOf("")
    var august by mutableStateOf("")
    var september by mutableStateOf("")
    var october by mutableStateOf("")
    var november by mutableStateOf("")
    var december by mutableStateOf("")

    init {
        getValues()
    }


    // Get values from DataStore using CoroutineScope
    fun getValues() = runBlocking {
        salary = repo.getDouble(SALARY, 0.0).toString()
        tax_deduction = repo.getDouble(TAX_DEDUCTION, 0.0).toString()
        tax_percentage = repo.getDouble(TAX_PERCENTAGE, 0.0).toString()
        tax_additional = repo.getDouble(TAX_ADDITIONAL, 0.0).toString()
        january = repo.getInt(JANUARY, 0).toString()
        february = repo.getInt(FEBRUARY, 0).toString()
        marts = repo.getInt(MARCH, 0).toString()
        april = repo.getInt(APRIL, 0).toString()
        may = repo.getInt(MAY, 0).toString()
        june = repo.getInt(JUNE, 0).toString()
        july = repo.getInt(JULY, 0).toString()
        august = repo.getInt(AUGUST, 0).toString()
        september = repo.getInt(SEPTEMBER, 0).toString()
        october = repo.getInt(OCTOBER, 0).toString()
        november = repo.getInt(NOVEMBER, 0).toString()
        december = repo.getInt(DECEMBER, 0).toString()
    }

    // Save values to DataStore using CoroutineScope
    fun save() = runBlocking {
        repo.setDouble(SALARY, salary.toDouble())

        repo.setDouble(TAX_DEDUCTION, tax_deduction.toDouble())
        repo.setDouble(TAX_PERCENTAGE, tax_percentage.toDouble())
        repo.setDouble(TAX_ADDITIONAL, tax_additional.toDouble())

        repo.setInt(JANUARY, january.toInt())
        repo.setInt(FEBRUARY, february.toInt())
        repo.setInt(MARCH, marts.toInt())
        repo.setInt(APRIL, april.toInt())
        repo.setInt(MAY, may.toInt())
        repo.setInt(JUNE, june.toInt())
        repo.setInt(JULY, july.toInt())
        repo.setInt(AUGUST, august.toInt())
        repo.setInt(SEPTEMBER, september.toInt())
        repo.setInt(OCTOBER, october.toInt())
        repo.setInt(NOVEMBER, november.toInt())
        repo.setInt(DECEMBER, december.toInt())
    }

}