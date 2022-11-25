package dtu.amd.worktracker.dal

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provide a database instance as singleton. Used to create a database instance
    // Object created as a singleton, so only one instance of the database is created
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "work.db"
        )
            .build()
    }

    // Provide a dao instance as singleton. Used to create a dao instance
    @Singleton
    @Provides
    fun provideWorkDao(database: AppDatabase) = database.workDao()
}