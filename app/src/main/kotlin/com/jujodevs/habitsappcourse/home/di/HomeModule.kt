package com.jujodevs.habitsappcourse.home.di

import com.jujodevs.habitsappcourse.home.data.repository.HomeRepositoryImpl
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository = HomeRepositoryImpl()
}