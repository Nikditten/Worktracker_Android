//package dtu.amd.worktracker.di
//
//import android.content.Context
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import dtu.amd.worktracker.repository.UserPreferencesRepository
//import dtu.amd.worktracker.repository.UserPreferencesRepositoryImpl
//import kotlinx.coroutines.CoroutineScope
//import javax.inject.Singleton
//
//// SOURCE: https://github.com/dhruvRj18/DataStoreYT/tree/master/app/src/main/java/com/dhruv/datastoreyt
//@Module
//@InstallIn(SingletonComponent::class)
//object DataStoreModule {
//    @Singleton
//    @Provides
//    fun providesUserPreferencesRepository(
//        @ApplicationContext context: Context,
//        @ApplicationIoScope applicationIoScope: CoroutineScope
//    ):UserPreferencesRepository = UserPreferencesRepositoryImpl(context, applicationIoScope)
//}