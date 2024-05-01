package com.jujodevs.habitsappcourse.home.api.domain.detail.usecase

import com.jujodevs.habitsappcourse.core.common.di.IO
import com.jujodevs.habitsappcourse.home.api.domain.models.Habit
import com.jujodevs.habitsappcourse.home.api.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHabitByIdUseCase @Inject constructor(
    private val repository: HomeRepository,
    @IO private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(habitId: String): Habit {
        return withContext(dispatcher) { repository.getHabitById(habitId) }
    }
}