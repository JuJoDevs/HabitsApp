package com.jujodevs.habitsappcourse.authentication.api.presentation.signup

sealed interface SignupEvent {
    data class EmailChange(val email: String) : SignupEvent
    data class PasswordChange(val password: String) : SignupEvent
    data object LogIn : SignupEvent
    data object SignUp : SignupEvent
}