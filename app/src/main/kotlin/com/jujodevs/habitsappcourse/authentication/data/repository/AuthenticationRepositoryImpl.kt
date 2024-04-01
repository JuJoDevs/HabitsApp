package com.jujodevs.habitsappcourse.authentication.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}