package com.jujodevs.habitsappcourse.home.api.presentation.home

import com.jujodevs.habitsappcourse.home.api.domain.models.Habit
import java.time.ZonedDateTime

data class HomeState(
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = emptyList(),
)
