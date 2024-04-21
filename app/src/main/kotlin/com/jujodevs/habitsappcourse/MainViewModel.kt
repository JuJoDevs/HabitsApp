package com.jujodevs.habitsappcourse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujodevs.habitsappcourse.authentication.domain.usecase.GetUserIdUseCase
import com.jujodevs.habitsappcourse.authentication.domain.usecase.LogoutUseCase
import com.jujodevs.habitsappcourse.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    getUserIdUseCase: GetUserIdUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
    var isLoggedIn by mutableStateOf(false)

    init {
        viewModelScope.launch {
            isLoggedIn = getUserIdUseCase() != null
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}