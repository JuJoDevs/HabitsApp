package com.jujodevs.habitsappcourse.authentication.domain.usecase

import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
) {
    suspend operator fun invoke() = repository.logout()
}