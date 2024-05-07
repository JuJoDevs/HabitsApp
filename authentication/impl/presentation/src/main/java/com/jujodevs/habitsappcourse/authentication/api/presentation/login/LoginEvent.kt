package com.jujodevs.habitsappcourse.authentication.api.presentation.login

sealed interface LoginEvent {
    data class EmailChange(val email: String) : LoginEvent
    data class PasswordChange(val password: String) : LoginEvent
    data object Login : LoginEvent
    data object SignUp : LoginEvent
    data object SignupDone : LoginEvent
}