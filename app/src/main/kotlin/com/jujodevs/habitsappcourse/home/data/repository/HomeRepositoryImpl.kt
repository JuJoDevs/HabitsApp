package com.jujodevs.habitsappcourse.home.data.repository

import android.content.SharedPreferences
import com.jujodevs.habitsappcourse.core.FIREBASE_TOKEN
import com.jujodevs.habitsappcourse.core.di.IO
import com.jujodevs.habitsappcourse.core.di.SharedPreferencesModule.FirebaseToken
import com.jujodevs.habitsappcourse.home.data.extension.toStartOfDateTimestamp
import com.jujodevs.habitsappcourse.home.data.local.HomeDao
import com.jujodevs.habitsappcourse.home.data.mapper.toDomain
import com.jujodevs.habitsappcourse.home.data.mapper.toDto
import com.jujodevs.habitsappcourse.home.data.mapper.toEntity
import com.jujodevs.habitsappcourse.home.data.remote.HomeApi
import com.jujodevs.habitsappcourse.home.data.remote.util.resultOf
import com.jujodevs.habitsappcourse.home.domain.alarm.AlarmHandler
import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dao: HomeDao,
    private val api: HomeApi,
    private val alarmHandler: AlarmHandler,
    @FirebaseToken private val sharedPreferences: SharedPreferences,
    @IO private val dispatcher: CoroutineDispatcher,
) : HomeRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> = flow {
        val localHabits = dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp())
            .first().toDomain()
        emit(localHabits)
        withContext(dispatcher) { getHabitsFromApi() }
        emit(localHabits)
    }.flatMapLatest {
        dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp())
            .map { it.toDomain() }
    }

    private suspend fun getHabitsFromApi() {
        resultOf {
            val token = sharedPreferences.getString(FIREBASE_TOKEN, null)
            val habits = api.getAllHabits(token).toDomain()
            insertHabits(habits)
        }
    }

    override suspend fun insertHabit(habit: Habit) {
        handleAlarm(habit)
        dao.insertHabit(habit.toEntity())
        resultOf {
            val token = sharedPreferences.getString(FIREBASE_TOKEN, null)
            api.insertHabit(token, habit.toDto())
        }
    }

    private suspend fun insertHabits(habits: List<Habit>) {
        habits.forEach {
            handleAlarm(it)
            dao.insertHabit(it.toEntity())
        }
    }

    private suspend fun handleAlarm(habit: Habit) {
        dao.getHabitById(habit.id)?.toDomain()?.let { alarmHandler.cancel(it) }
        alarmHandler.setRecurringAlarm(habit)
    }

    override suspend fun getHabitById(habitId: String): Habit {
        return dao.getHabitById(habitId)?.toDomain() ?: throw Exception("Habit not found")
    }
}