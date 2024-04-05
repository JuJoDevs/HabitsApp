package com.jujodevs.habitsappcourse.home.domain.detail.usecase

import javax.inject.Inject

data class DetailUseCases @Inject constructor(
    val getHabitById: GetHabitByIdUseCase,
    val insertHabit: InsertHabitUseCase,
)
