package com.jujodevs.habitsappcourse.onboarding.domain.repository

interface OnboardingRepository {
    fun hasSeenOnboarding(): Boolean
    fun completeOnboarding()
}