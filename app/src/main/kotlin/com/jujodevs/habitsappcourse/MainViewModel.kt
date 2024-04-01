package com.jujodevs.habitsappcourse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jujodevs.habitsappcourse.authentication.domain.usecase.GetUserIdUseCase
import com.jujodevs.habitsappcourse.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
}