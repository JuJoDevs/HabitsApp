package com.jujodevs.habitsappcourse.authentication.presentation.signup

sealed interface SignupEvent {
    data class EmailChange(val email: String) : SignupEvent
    data class PasswordChange(val password: String) : SignupEvent
    data object SignIn : SignupEvent
    data object SignUp : SignupEvent
}