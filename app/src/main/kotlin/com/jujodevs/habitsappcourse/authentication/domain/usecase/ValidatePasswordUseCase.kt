package com.jujodevs.habitsappcourse.authentication.domain.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): PasswordResult {
        return when {
            password.length < 8 -> PasswordResult.Invalid(
                "La contraseña tiene que tener al menos 8 caracteres"
            )

            password.none { it.isLowerCase() } -> PasswordResult.Invalid(
                "La contraseña tiene que tener al menos una letra minúscula"
            )

            password.none { it.isUpperCase() } -> PasswordResult.Invalid(
                "La contraseña tiene que tener al menos una letra mayúscula"
            )

            password.none { it.isDigit() } -> PasswordResult.Invalid(
                "La contraseña tiene que tener al menos un número"
            )

            else -> PasswordResult.Valid
        }
    }
}

sealed class PasswordResult() {
    data object Valid : PasswordResult()
    data class Invalid(val errorMessage: String) : PasswordResult()
}
