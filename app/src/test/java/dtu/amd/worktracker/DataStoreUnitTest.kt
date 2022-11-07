package dtu.amd.worktracker

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dtu.amd.worktracker.repository.DataStoreRepository
import dtu.amd.worktracker.repository.DataStoreRepositoryImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [31])
class DataStoreUnitTest {

    private lateinit var dataStoreRepository: DataStoreRepository

    @Before
    fun setup() {
        dataStoreRepository = DataStoreRepositoryImpl(context = ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testString() = runBlocking {
        dataStoreRepository.setString("test", "this is a test")
        val test = dataStoreRepository.getString("test", "error")
        assertEquals("this is a test", test)
    }

    @Test
    fun testDouble() = runBlocking {
        dataStoreRepository.setDouble("test", 1010.10)
        val test = dataStoreRepository.getDouble("test", -1.0)
        assertEquals(1010.10, test)
    }

    @Test
    fun testInt() = runBlocking {
        dataStoreRepository.setInt("test", 2020)
        val test = dataStoreRepository.getInt("test", -1)
        assertEquals(2020, test)
    }


}