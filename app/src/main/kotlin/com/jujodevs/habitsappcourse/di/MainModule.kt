package com.jujodevs.habitsappcourse.di

import com.jujodevs.habitsappcourse.MainActivity
import com.jujodevs.habitsappcourse.home.api.data.alarm.NotificationNavigationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideNotificationNavigationHandler(
        mainActivity: MainActivity,
    ): NotificationNavigationHandler = mainActivity
}