package com.jujodevs.habitsappcourse.home.api.domain.detail.usecase

import javax.inject.Inject

data class DetailUseCases @Inject constructor(
    val getHabitById: GetHabitByIdUseCase,
    val insertHabit: InsertHabitUseCase,
)
