package com.jujodevs.habitsappcourse.onboarding.di

import com.jujodevs.habitsappcourse.onboarding.api.data.di.OnboardingModule
import com.jujodevs.habitsappcourse.onboarding.api.domain.repository.OnboardingRepository
import com.jujodevs.habitsappcourse.onboarding.data.repository.FakeOnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [OnboardingModule::class]
)
object TestOboardingModule {
    @Provides
    @Singleton
    fun provideOnboardingRepository(): OnboardingRepository = FakeOnboardingRepository()
}