package com.jujodevs.habitsappcourse.home.di

import com.jujodevs.habitsappcourse.home.api.data.di.HomeRepositoryModule
import com.jujodevs.habitsappcourse.home.api.domain.repository.HomeRepository
import com.jujodevs.habitsappcourse.home.data.repository.FakeHomeRepository
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