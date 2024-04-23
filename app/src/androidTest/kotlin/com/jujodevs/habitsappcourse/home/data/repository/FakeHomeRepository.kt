package com.jujodevs.habitsappcourse.home.data.repository

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.ZonedDateTime

class FakeHomeRepository : HomeRepository {
    private var habits = emptyList<Habit>()
    private val habitsFlow = MutableSharedFlow<List<Habit>>()
    override fun getAllHabitsForSelectedDate(date: ZonedDateTime) = habitsFlow

    override suspend fun insertHabit(habit: Habit) {
        habits = habits + habit
        habitsFlow.emit(habits)
    }

    override suspend fun getHabitById(habitId: String) =
        habits.first { it.id == habitId }

    override fun syncHabits() {}
}