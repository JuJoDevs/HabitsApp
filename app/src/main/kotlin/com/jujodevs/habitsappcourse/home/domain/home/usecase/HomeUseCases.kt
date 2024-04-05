package com.jujodevs.habitsappcourse.home.domain.home.usecase

import javax.inject.Inject

class HomeUseCases @Inject constructor(
    val getHabitsForSelectedUseCase: GetHabitsForSelectedUseCase,
    val completeHabitUseCase: CompleteHabitUseCase,
)