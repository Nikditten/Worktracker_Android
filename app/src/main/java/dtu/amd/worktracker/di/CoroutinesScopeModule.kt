package dtu.amd.worktracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

// https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationCoroutineScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationIoScope


@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

    // provide a CoroutineScope with a SupervisorJob and the main dispatcher as singleton
    @Singleton
    @ApplicationCoroutineScope
    @Provides
    fun provideApplicationCoroutineScope(
        @MainDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

    // provide a IO CoroutineScope with a SupervisorJob and the io dispatcher as singleton
    @Singleton
    @ApplicationIoScope
    @Provides
    fun provideApplicationIoScope(
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

}