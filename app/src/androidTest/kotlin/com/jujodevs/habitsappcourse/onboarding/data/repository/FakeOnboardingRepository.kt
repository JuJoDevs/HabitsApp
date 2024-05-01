package com.jujodevs.habitsappcourse.onboarding.data.repository

import com.jujodevs.habitsappcourse.onboarding.api.domain.repository.OnboardingRepository

class FakeOnboardingRepository : OnboardingRepository {
    var hasSeenOnboarding = false
    override fun hasSeenOnboarding(): Boolean = hasSeenOnboarding

    override fun completeOnboarding() {
        hasSeenOnboarding = true
    }
}