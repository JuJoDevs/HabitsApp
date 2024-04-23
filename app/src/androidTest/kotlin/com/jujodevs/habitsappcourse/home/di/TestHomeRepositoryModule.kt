package com.jujodevs.habitsappcourse.home.di

import com.jujodevs.habitsappcourse.home.data.repository.FakeHomeRepository
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [HomeRepositoryModule::class]
)
object TestHomeRepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository = FakeHomeRepository()
}