package com.jujodevs.habitsappcourse.authentication.api.presentation.utils

import com.jujodevs.habitsappcourse.authentication.domain.usecase.PasswordResult

object PasswordErrorParser {
    fun parseError(error: PasswordResult): String? {
        return when (error) {
            PasswordResult.VALID -> null
            PasswordResult.INVALID_LOWERCASE -> "La contraseña tiene que tener al menos una letra minúscula"
            PasswordResult.INVALID_UPPERCASE -> "La contraseña tiene que tener al menos una letra mayúscula"
            PasswordResult.INVALID_DIGITS -> "La contraseña tiene que tener al menos un número"
            PasswordResult.INVALID_LENGTH -> "La contraseña tiene que tener al menos 8 caracteres"
        }
    }
}