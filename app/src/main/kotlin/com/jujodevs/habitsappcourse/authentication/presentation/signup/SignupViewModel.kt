package com.jujodevs.habitsappcourse.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujodevs.habitsappcourse.authentication.domain.usecase.SignupUseCases
import com.jujodevs.habitsappcourse.authentication.presentation.utils.PasswordErrorParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCases: SignupUseCases,
) : ViewModel() {
    var state by mutableStateOf(SignupState())
        private set

    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EmailChange ->
                state = state.copy(email = event.email)

            is SignupEvent.PasswordChange ->
                state = state.copy(password = event.password)

            SignupEvent.LogIn ->
                state = state.copy(logIn = true)

            SignupEvent.SignUp ->
                signUp()
        }
    }

    private fun signUp() {
        state = if (!signupUseCases.validateEmail(state.email)) {
            state.copy(emailError = "El email no es válido")
        } else {
            state.copy(emailError = null)
        }
        val passwordResult = signupUseCases.validatePassword(state.password)
        state = state.copy(passwordError = PasswordErrorParser.parseError(passwordResult))

        if (state.emailError != null || state.passwordError != null) return

        isLoading(true)

        viewModelScope.launch {
            signupUseCases.signupWithEmail(state.email, state.password).onSuccess {
                state = state.copy(isSignedIn = true)
            }.onFailure {
                state = state.copy(emailError = it.message)
            }
            isLoading(false)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }
}