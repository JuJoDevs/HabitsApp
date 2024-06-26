package com.jujodevs.habitsappcourse.onboarding.api.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jujodevs.habitsappcourse.onboarding.api.data.repository.ObboardingRepositoryImpl
import com.jujodevs.habitsappcourse.onboarding.api.domain.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    @OnboardingPreferences
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences("habits_onboarding_preferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        @OnboardingPreferences sharedPreferences: SharedPreferences,
    ): OnboardingRepository = ObboardingRepositoryImpl(sharedPreferences)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class OnboardingPreferences
