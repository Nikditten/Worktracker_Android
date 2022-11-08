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

// SOURCE: https://github.com/dhruvRj18/DataStoreYT

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {
    override suspend fun setString(key: String, value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun setDouble(key: String, value: Double) {
        context.dataStore.edit { preferences ->
            preferences[doublePreferencesKey(key)] = value
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    override suspend fun getString(key: String, default: String): String {
        return context.dataStore.data.first()[stringPreferencesKey(key)] ?: default
    }

    override suspend fun getDouble(key: String, default: Double): Double {
        return context.dataStore.data.first()[doublePreferencesKey(key)] ?: default
    }

    override suspend fun getInt(key: String, default: Int): Int {
        return context.dataStore.data.first()[intPreferencesKey(key)] ?: default
    }

    override suspend fun getSalaryPeriod(): List<Int> {
        val salaryPeriod = mutableListOf<Int>(Date().AsYear())
        val currentMonth: Int = Date().AsMonth()
        val lastDayInMonth: Int = getInt("month_$currentMonth", -1)
        if (lastDayInMonth != -1 && lastDayInMonth < Date().AsDay()) {
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
