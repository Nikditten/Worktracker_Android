package dtu.amd.worktracker

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dtu.amd.worktracker.dal.AppDatabase
import dtu.amd.worktracker.dal.dao.WorkDao
import dtu.amd.worktracker.dal.model.Work
import dtu.amd.worktracker.util.asMonth
import dtu.amd.worktracker.util.asYear
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.*


// SOURCE: https://medium.com/@wambuinjumbi/unit-testing-in-android-room-361bf56b69c5


@RunWith(AndroidJUnit4::class)
@Config(sdk = [31])
class DatabaseUnitTest {

    private lateinit var database: AppDatabase
    private lateinit var workDao: WorkDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        workDao = database.workDao()
    }

    @Test
    fun testAddToDatabase() = runBlocking {

        val work = Work(
            id = 0,
            title = "This is a test",
            company = "DTU Ballerup",
            date = Date(),
            start = Date(),
            end = Date(),
            lunch_held = true,
            lunch_start = Date(),
            lunch_end = Date(),
            paid = 180.0 * 4.0,
            hourly_rate = 180.0,
            hours = 4.0,
            salary_period_month = Date().asMonth(),
            salary_period_year = Date().asYear()
        )

        workDao.addWork(work)

        val fetchedWork = workDao.getAllWork().first()

        assert(fetchedWork.isNotEmpty())
        assert(fetchedWork.first().title == "This is a test")
    }

    @Test
    fun testDeleteFromDatabase() = runBlocking {

        val work = Work(
            id = 0,
            title = "This is a test",
            company = "DTU Ballerup",
            date = Date(),
            start = Date(),
            end = Date(),
            lunch_held = true,
            lunch_start = Date(),
            lunch_end = Date(),
            paid = 180.0 * 4.0,
            hourly_rate = 180.0,
            hours = 4.0,
            salary_period_month = Date().asMonth(),
            salary_period_year = Date().asYear()
        )

        workDao.addWork(work)

        val fetchedWork = workDao.getAllWork().first()

        assert(fetchedWork.count() == 1)

        val id: Int = fetchedWork.first().id

        workDao.deleteWork(id)

        assert(workDao.getAllWork().first().isEmpty())
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}