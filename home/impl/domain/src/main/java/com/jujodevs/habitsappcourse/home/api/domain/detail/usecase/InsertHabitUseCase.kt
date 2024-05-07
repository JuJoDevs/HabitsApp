package com.jujodevs.habitsappcourse.home.api.domain.detail.usecase

import com.jujodevs.habitsappcourse.home.api.domain.models.Habit
import com.jujodevs.habitsappcourse.home.api.domain.repository.HomeRepository
import javax.inject.Inject

class InsertHabitUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(habit: Habit) {
        repository.insertHabit(habit)
    }
}