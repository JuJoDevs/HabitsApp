package com.jujodevs.habitsappcourse.home.domain.detail.usecase

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetHabitByIdUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(habitId: String): Habit {
        return repository.getHabitById(habitId)
    }
}