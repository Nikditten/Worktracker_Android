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
//For example, to install a module so that anything in the application can use it,
// use SingletonComponent
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    // Provide a data store repository instance as singleton. Used to create a data store repository instance
    @Singleton // Exists as long as the application exists
    @Provides
    fun providesDatastoreRepo(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }
}