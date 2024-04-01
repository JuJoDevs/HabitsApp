package com.jujodevs.habitsappcourse.authentication.domain.usecase

import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
) {
    operator fun invoke(): String? = repository.getUserId()
}