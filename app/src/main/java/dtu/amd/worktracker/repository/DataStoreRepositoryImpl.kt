package dtu.amd.worktracker.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dtu.amd.worktracker.di.ApplicationIoScope
import dtu.amd.worktracker.util.AsDay
import dtu.amd.worktracker.util.AsMonth
import dtu.amd.worktracker.util.AsYear
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

// SOURCE: https://github.com/dhruvRj18/DataStoreYT

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    // Set a string value in the data store
    override suspend fun setString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    // Set a double value in the data store
    override suspend fun setDouble(key: String, value: Double) {
        context.dataStore.edit { preferences ->
            preferences[doublePreferencesKey(key)] = value
        }
    }

    // Set an integer value in the data store
    override suspend fun setInt(key: String, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    // Get a string value from the data store
    override suspend fun getString(key: String, default: String): String {
        return context.dataStore.data.first()[stringPreferencesKey(key)] ?: default
    }

    // Get a double value from the data store
    override suspend fun getDouble(key: String, default: Double): Double {
        return context.dataStore.data.first()[doublePreferencesKey(key)] ?: default
    }

    // Get an integer value from the data store
    override suspend fun getInt(key: String, default: Int): Int {
        return context.dataStore.data.first()[intPreferencesKey(key)] ?: default
    }

    // Get the salary period from the data store
    override suspend fun getSalaryPeriod(): List<Int> {
        // define salaryperiod as a list and initialize it with the current year
        val salaryPeriod = mutableListOf<Int>(Date().AsYear())
        // Get current month
        val currentMonth: Int = Date().AsMonth()
        // Get laste date of current month
        val lastDayInMonth: Int = getInt("month_$currentMonth", -1)
        // Check for correct salary month and insert it into the salary period list
        if (lastDayInMonth > 0 && lastDayInMonth < Date().AsDay()) {
            if (currentMonth == 12) {
                salaryPeriod.add(1)
            } else {
                salaryPeriod.add(currentMonth + 1)
            }
        } else {
            salaryPeriod.add(currentMonth)
        }
        return salaryPeriod
    }


}
