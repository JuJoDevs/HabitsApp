package com.jujodevs.habitsappcourse.onboarding.api.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujodevs.habitsappcourse.onboarding.api.domain.usecase.CompleteOnboarding
import com.jujodevs.habitsappcourse.onboarding.api.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val completeOnboardingUseCase: CompleteOnboarding
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    fun completeOnboarding() {
        completeOnboardingUseCase()
        hasSeenOnboarding = true
    }
}