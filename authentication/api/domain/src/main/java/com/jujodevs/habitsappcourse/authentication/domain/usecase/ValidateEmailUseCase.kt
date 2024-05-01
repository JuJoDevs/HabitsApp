package com.jujodevs.habitsappcourse.authentication.domain.usecase

import com.jujodevs.habitsappcourse.authentication.domain.matcher.EmailMatcher
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val matcher: EmailMatcher,
) {
    operator fun invoke(email: String): Boolean {
        return matcher.isValid(email)
    }
}