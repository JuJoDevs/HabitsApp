package com.jujodevs.habitsappcourse.home.data.sync

import android.content.Context
import android.content.SharedPreferences
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jujodevs.core.domain.FIREBASE_TOKEN
import com.jujodevs.habitsappcourse.core.data.di.IO
import com.jujodevs.habitsappcourse.core.data.di.SharedPreferencesModule.FirebaseToken
import com.jujodevs.habitsappcourse.home.data.local.HomeDao
import com.jujodevs.habitsappcourse.home.data.local.entity.HabitSyncEntity
import com.jujodevs.habitsappcourse.home.data.mapper.toDomain
import com.jujodevs.habitsappcourse.home.data.mapper.toDto
import com.jujodevs.habitsappcourse.home.data.remote.HomeApi
import com.jujodevs.habitsappcourse.home.data.remote.util.resultOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

@HiltWorker
class HabitSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val api: HomeApi,
    private val dao: HomeDao,
    @FirebaseToken private val sharedPreferences: SharedPreferences,
    @IO private val dispatcher: CoroutineDispatcher,
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val items = dao.getAllHabitsSync()
        if (runAttemptCount >= 3) {
            return Result.failure()
        }

        return try {
            supervisorScope {
                val jobs = items.map { item ->
                    async(dispatcher) { sync(item) }
                }
                jobs.awaitAll()
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun sync(habitSyncEntity: HabitSyncEntity) {
        val habit = dao.getHabitById(habitSyncEntity.id)?.toDomain()?.toDto()
        habit?.let {
            resultOf {
                val token = sharedPreferences.getString(FIREBASE_TOKEN, null)
                api.insertHabit(token, it)
            }.onSuccess {
                dao.deleteHabitSync(habitSyncEntity)
            }.onFailure {
                throw it
            }
        } ?: dao.deleteHabitSync(habitSyncEntity)
    }
}