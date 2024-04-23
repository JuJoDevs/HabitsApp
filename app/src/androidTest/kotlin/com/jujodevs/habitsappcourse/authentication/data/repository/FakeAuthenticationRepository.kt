package com.jujodevs.habitsappcourse.authentication.data.repository

import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository

class FakeAuthenticationRepository : AuthenticationRepository {
    var fakeError = false
    var isLogin = false
    val fakeErrorMessage = "There was a server error!"
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (fakeError) Result.failure(Exception(fakeErrorMessage)) else {
            isLogin = true
            Result.success(Unit)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return if (fakeError) Result.failure(Exception(fakeErrorMessage)) else {
            isLogin = true
            Result.success(Unit)
        }
    }

    override suspend fun getUserId(): String? {
        return if (isLogin) "USER_ID" else null
    }

    override suspend fun logout() {}
}