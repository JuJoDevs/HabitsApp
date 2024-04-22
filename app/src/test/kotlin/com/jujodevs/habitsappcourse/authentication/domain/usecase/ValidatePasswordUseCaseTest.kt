package com.jujodevs.habitsappcourse.authentication.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setup() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `given low character password, return invalid password`() {
        val input = "asd"
        val result = validatePasswordUseCase(input)

        assertEquals(
            result,
            PasswordResult.INVALID_LENGTH
        )
    }

    @Test
    fun `given no lowercase character password, return invalid password`() {
        val input = "ASDASDASD"
        val result = validatePasswordUseCase(input)

        assertEquals(
            result,
            PasswordResult.INVALID_LOWERCASE
        )
    }

    @Test
    fun `given no uppercase character password, return invalid password`() {
        val input = "asdasdasd"
        val result = validatePasswordUseCase(input)

        assertEquals(
            result,
            PasswordResult.INVALID_UPPERCASE
        )
    }

    @Test
    fun `given no numbered character password, return invalid password`() {
        val input = "ASDASDASDasdasdasd"
        val result = validatePasswordUseCase(input)

        assertEquals(
            result,
            PasswordResult.INVALID_DIGITS
        )
    }

    @Test
    fun `given success password, return valid password`() {
        val input = "ASDASDASDasdasdasd123123123"
        val result = validatePasswordUseCase(input)

        assertEquals(
            result,
            PasswordResult.VALID
        )
    }
}
