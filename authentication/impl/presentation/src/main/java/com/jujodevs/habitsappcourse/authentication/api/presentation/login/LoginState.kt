package com.jujodevs.habitsappcourse.authentication.api.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val signup: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
)
