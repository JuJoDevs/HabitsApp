package com.jujodevs.habitsappcourse.authentication.data.repository

import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jujodevs.habitsappcourse.authentication.domain.repository.AuthenticationRepository
import com.jujodevs.habitsappcourse.core.FIREBASE_TOKEN
import com.jujodevs.habitsappcourse.core.data.network.isOnline
import com.jujodevs.habitsappcourse.core.di.SharedPreferencesModule.FirebaseToken
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    @FirebaseToken private val sharedPreferences: SharedPreferences,
) : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val result = Firebase.auth.signInWithEmailAndPassword(email, password).await()
            setToken(result.user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            val result = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            setToken(result.user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun setToken(user: FirebaseUser?) {
        val token = user?.getIdToken(true)?.await()?.token
        sharedPreferences.edit().putString(FIREBASE_TOKEN, token).apply()
    }

    override suspend fun getUserId(): String? {
        return try {
            if (connectivityManager.isOnline()) {
                val user = Firebase.auth.currentUser
                setToken(user)
                user?.uid
            } else {
                ""
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }
}