package dtu.amd.worktracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

// https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room
@InstallIn(SingletonComponent::class)
@Module
object CoroutinesDispatchersModule {

    // Dispatcher = Defines thread pools to launch your Kotlin Coroutines in.
    // There are majorly 4 types of Dispatchers: Main, IO, Default, Unconfined.


    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}