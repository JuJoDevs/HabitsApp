package com.jujodevs.habitsappcourse.authentication.domain.usecase

import javax.inject.Inject

data class LoginUseCases @Inject constructor(
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val loginWithEmail: LoginWithEmailUseCase,
)
