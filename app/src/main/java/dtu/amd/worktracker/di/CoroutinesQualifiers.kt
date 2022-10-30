package dtu.amd.worktracker.di

import javax.inject.Qualifier

// https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

