package com.jujodevs.habitsappcourse.home.domain.home.usecase

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import java.time.ZonedDateTime
import javax.inject.Inject

class CompleteHabitUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(habit: Habit, date: ZonedDateTime) {
        val newHabit = if (habit.completedDates.contains(date.toLocalDate())) {
            habit.copy(completedDates = habit.completedDates - date.toLocalDate())
        } else {
            habit.copy(completedDates = habit.completedDates + date.toLocalDate())
        }
        repository.insertHabit(newHabit)
    }
}