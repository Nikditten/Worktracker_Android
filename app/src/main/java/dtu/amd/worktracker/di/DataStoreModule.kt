package dtu.amd.worktracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dtu.amd.worktracker.repository.DataStoreRepository
import dtu.amd.worktracker.repository.DataStoreRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

// SOURCE: https://github.com/dhruvRj18/DataStoreYT

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun providesDatstoreRepo(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }
}