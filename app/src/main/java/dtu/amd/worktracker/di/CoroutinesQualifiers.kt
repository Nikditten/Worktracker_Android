package dtu.amd.worktracker.di

import javax.inject.Qualifier

// https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
// SOURCE: https://github.com/HenrikPihl/retrofit_room/tree/feature/add-room

// Qualifier for the default dispatcher
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

// Qualifier for the main dispatcher
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

// Qualifier for the IO dispatcher
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

