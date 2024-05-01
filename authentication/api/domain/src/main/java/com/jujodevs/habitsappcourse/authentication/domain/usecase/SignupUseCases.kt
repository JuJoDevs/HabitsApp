package com.jujodevs.habitsappcourse.authentication.domain.usecase

import javax.inject.Inject

data class SignupUseCases @Inject constructor(
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val signupWithEmail: SignupWithEmailUseCase,
)