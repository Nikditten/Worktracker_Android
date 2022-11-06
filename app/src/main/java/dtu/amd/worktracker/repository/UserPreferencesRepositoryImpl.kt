//package dtu.amd.worktracker.repository
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.*
//import androidx.datastore.preferences.preferencesDataStore
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dtu.amd.worktracker.di.ApplicationIoScope
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//// SOURCE: https://github.com/dhruvRj18/DataStoreYT/blob/master/app/src/main/java/com/dhruv/datastoreyt/DataStoreRepoImpl.kt
//
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
//
//class UserPreferencesRepositoryImpl @Inject constructor(
//    private val context: Context,
//    private val applicationIoScope: CoroutineScope
//) : UserPreferencesRepository {
//
//    override fun setDouble(key: String, value: Double) {
//        applicationIoScope.launch {
//            val prefereneKey = doublePreferencesKey(key)
//            context.dataStore.edit {
//                it[prefereneKey] = value
//            }
//        }
//    }
//
//    override fun setInt(key: String, value: Int) {
//        applicationIoScope.launch {
//            val prefereneKey = intPreferencesKey(key)
//            context.dataStore.edit {
//                it[prefereneKey] = value
//            }
//        }
//    }
//
//    override fun getDouble(key: String): Double {
//        var preferenceValue: Double = 0.0
//        applicationIoScope.launch {
//            try {
//                val preferenceKey = doublePreferencesKey(key)
//                val preference = context.dataStore.data.first()
//                preferenceValue = preference[preferenceKey] ?: 0.0
//            } catch (e: Exception) {
//                e.printStackTrace()
//                preferenceValue = 0.0
//            }
//        }
//        return preferenceValue
//    }
//
//    override fun getInt(key: String): Int {
//        var preferenceValue: Int = 0
//        applicationIoScope.launch {
//            try {
//                val preferenceKey = intPreferencesKey(key)
//                val preference = context.dataStore.data.first()
//                preferenceValue = preference[preferenceKey] ?: 0
//            } catch (e: Exception) {
//                e.printStackTrace()
//                preferenceValue = 0
//            }
//        }
//        return preferenceValue
//    }
//
//}