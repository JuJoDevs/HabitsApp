package com.jujodevs.habitsappcourse.home.api.domain.repository

import com.jujodevs.habitsappcourse.home.api.domain.models.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

interface HomeRepository {
    fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>>
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(habitId: String): Habit
    fun syncHabits()
}


