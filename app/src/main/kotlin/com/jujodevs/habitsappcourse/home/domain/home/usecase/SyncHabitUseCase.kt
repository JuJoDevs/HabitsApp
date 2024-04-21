package com.jujodevs.habitsappcourse.home.domain.home.usecase

import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import javax.inject.Inject

class SyncHabitUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke() {
        repository.syncHabits()
    }
}