package com.jujodevs.habitsappcourse.authentication.di

import com.jujodevs.habitsappcourse.authentication.data.matcher.EmailMatcherImpl
import com.jujodevs.habitsappcourse.authentication.data.repository.FakeAuthenticationRepository
import com.jujodevs.habitsappcourse.authentication.domain.matcher.EmailMatcher
import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AuthenticationModule::class]
)
object TestAuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository =
        FakeAuthenticationRepository()

    @Provides
    @Singleton
    fun provideEmailMarcher(): EmailMatcher = EmailMatcherImpl()
}