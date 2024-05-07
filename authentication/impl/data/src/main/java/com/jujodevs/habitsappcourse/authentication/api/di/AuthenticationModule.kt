package com.jujodevs.habitsappcourse.authentication.api.di

import com.jujodevs.habitsappcourse.authentication.api.data.matcher.EmailMatcherImpl
import com.jujodevs.habitsappcourse.authentication.api.data.repository.AuthenticationRepositoryImpl
import com.jujodevs.habitsappcourse.authentication.domain.matcher.EmailMatcher
import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl,
    ): AuthenticationRepository = authenticationRepositoryImpl

    @Provides
    @Singleton
    fun provideEmailMarcher(): EmailMatcher = EmailMatcherImpl()
}