package com.jujodevs.habitsappcourse.home.api.domain.home.usecase

import javax.inject.Inject

class HomeUseCases @Inject constructor(
    val getHabitsForSelectedUseCase: GetHabitsForSelectedUseCase,
    val completeHabitUseCase: CompleteHabitUseCase,
    val syncHabitsUseCase: SyncHabitUseCase,
)