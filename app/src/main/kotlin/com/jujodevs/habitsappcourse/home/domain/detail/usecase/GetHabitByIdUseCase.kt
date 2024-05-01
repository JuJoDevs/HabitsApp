package com.jujodevs.habitsappcourse.home.domain.detail.usecase

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHabitByIdUseCase @Inject constructor(
    private val repository: HomeRepository,
    @com.jujodevs.habitsappcourse.core.common.di.IO private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(habitId: String): Habit {
        return withContext(dispatcher) { repository.getHabitById(habitId) }
    }
}