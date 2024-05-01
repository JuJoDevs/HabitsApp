package com.jujodevs.habitsappcourse.home.api.domain.home.usecase

import com.jujodevs.habitsappcourse.home.api.domain.repository.HomeRepository
import javax.inject.Inject

class SyncHabitUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    operator fun invoke() {
        repository.syncHabits()
    }
}