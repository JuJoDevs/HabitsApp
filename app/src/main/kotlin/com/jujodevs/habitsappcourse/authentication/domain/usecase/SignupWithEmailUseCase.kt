package com.jujodevs.habitsappcourse.authentication.domain.usecase

import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignupWithEmailUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signUp(email, password)
    }
}