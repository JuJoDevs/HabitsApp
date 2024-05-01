package com.jujodevs.habitsappcourse.authentication.presentation.login

import com.jujodevs.habitsappcourse.authentication.api.presentation.login.LoginEvent
import com.jujodevs.habitsappcourse.authentication.api.presentation.login.LoginState
import com.jujodevs.habitsappcourse.authentication.api.presentation.login.LoginViewModel
import com.jujodevs.habitsappcourse.authentication.data.repository.FakeAuthenticationRepository
import com.jujodevs.habitsappcourse.authentication.domain.matcher.EmailMatcher
import com.jujodevs.habitsappcourse.authentication.domain.usecase.LoginUseCases
import com.jujodevs.habitsappcourse.authentication.domain.usecase.LoginWithEmailUseCase
import com.jujodevs.habitsappcourse.authentication.domain.usecase.ValidateEmailUseCase
import com.jujodevs.habitsappcourse.authentication.domain.usecase.ValidatePasswordUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var authenticationRepository: FakeAuthenticationRepository

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        authenticationRepository = FakeAuthenticationRepository()
        val usecases = LoginUseCases(
            loginWithEmail = LoginWithEmailUseCase(authenticationRepository),
            validatePassword = ValidatePasswordUseCase(),
            validateEmail = ValidateEmailUseCase(object : EmailMatcher {
                override fun isValid(email: String): Boolean {
                    return email.isNotEmpty()
                }
            }),
        )
        loginViewModel = LoginViewModel(usecases, dispatcher)
    }

    @Test
    fun `initial state is empty`() {
        val state = loginViewModel.state

        assertEquals(
            LoginState(
                email = "",
                password = "",
                emailError = null,
                passwordError = null,
                signup = false,
                isLoggedIn = false,
                isLoading = false
            ),
            state
        )
    }

    @Test
    fun `given an email, verify the state updates the email`() {
        var state = loginViewModel.state.email
        assertEquals(state, "")

        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))

        state = loginViewModel.state.email
        assertEquals(
            "asd@asd.com",
            state
        )
    }

    @Test
    fun `given invalid email, show email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange(""))

        loginViewModel.onEvent(LoginEvent.Login)

        val state = loginViewModel.state
        assertNotNull(state.emailError)
    }

    @Test
    fun `set valid email, Login, no email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange("whatever"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assert(state.emailError == null)
    }

    @Test
    fun `set invalid password, Login, show password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asd"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.passwordError)
    }

    @Test
    fun `set valid password, Login, no password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNull(state.passwordError)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `set valid details, Login, starts loading and then logs in`() = scope.runTest {
        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(LoginEvent.Login)

        var state = loginViewModel.state
        assertNull(state.passwordError)
        assertNull(state.emailError)
        assert(state.isLoading)
        advanceUntilIdle()
        state = loginViewModel.state
        assert(state.isLoggedIn)
        assert(!state.isLoading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `set valid details but server error, Login, starts loading and then show error`() =
        scope.runTest {
            authenticationRepository.fakeError = true
            loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
            loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
            loginViewModel.onEvent(LoginEvent.Login)

            var state = loginViewModel.state
            assertNull(state.passwordError)
            assertNull(state.emailError)
            assert(state.isLoading)
            advanceUntilIdle()
            state = loginViewModel.state
            assert(!state.isLoggedIn)
            assert(!state.isLoading)
            assert(state.emailError == authenticationRepository.fakeErrorMessage)
        }
}