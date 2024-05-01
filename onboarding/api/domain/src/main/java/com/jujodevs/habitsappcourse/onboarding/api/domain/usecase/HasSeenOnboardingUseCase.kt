package com.jujodevs.habitsappcourse.onboarding.api.domain.usecase

import com.jujodevs.habitsappcourse.onboarding.api.domain.repository.OnboardingRepository
import javax.inject.Inject

class HasSeenOnboardingUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Boolean = repository.hasSeenOnboarding()
}