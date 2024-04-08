package com.jujodevs.habitsappcourse.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispatcherModule {
    @Main
    @Provides
    @Singleton
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @IO
    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IO

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Main
