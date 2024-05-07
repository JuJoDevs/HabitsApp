package com.jujodevs.habitsappcourse.authentication.domain.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): PasswordResult {
        return when {
            password.length < 8 -> PasswordResult.INVALID_LENGTH

            password.none { it.isLowerCase() } -> PasswordResult.INVALID_LOWERCASE

            password.none { it.isUpperCase() } -> PasswordResult.INVALID_UPPERCASE

            password.none { it.isDigit() } -> PasswordResult.INVALID_DIGITS

            else -> PasswordResult.VALID
        }
    }
}

enum class PasswordResult {
    VALID,
    INVALID_LOWERCASE,
    INVALID_UPPERCASE,
    INVALID_DIGITS,
    INVALID_LENGTH,
}
