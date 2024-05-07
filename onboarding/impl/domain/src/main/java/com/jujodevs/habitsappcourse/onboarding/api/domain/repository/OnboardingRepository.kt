package com.jujodevs.habitsappcourse.onboarding.api.domain.repository

interface OnboardingRepository {
    fun hasSeenOnboarding(): Boolean
    fun completeOnboarding()
}