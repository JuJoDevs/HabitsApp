package com.jujodevs.habitsappcourse.authentication.api.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujodevs.habitsappcourse.authentication.api.presentation.utils.PasswordErrorParser
import com.jujodevs.habitsappcourse.authentication.domain.usecase.LoginUseCases
import com.jujodevs.habitsappcourse.core.common.di.IO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
    @IO private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange ->
                state = state.copy(email = event.email)

            LoginEvent.Login ->
                login()

            is LoginEvent.PasswordChange ->
                state = state.copy(password = event.password)

            LoginEvent.SignUp ->
                state = state.copy(signup = true)

            LoginEvent.SignupDone ->
                state = state.copy(signup = false)
        }
    }

    private fun login() {
        state = if (!loginUseCases.validateEmail(state.email)) {
            state.copy(emailError = "El email no es válido")
        } else {
            state.copy(emailError = null)
        }
        val passwordResult = loginUseCases.validatePassword(state.password)
        state = state.copy(passwordError = PasswordErrorParser.parseError(passwordResult))

        if (state.emailError != null || state.passwordError != null) return

        isLoading(true)

        viewModelScope.launch(dispatcher) {
            loginUseCases.loginWithEmail(state.email, state.password).onSuccess {
                state = state.copy(isLoggedIn = true)
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