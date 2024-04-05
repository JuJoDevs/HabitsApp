package com.jujodevs.habitsappcourse.home.presentation.home

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import java.time.ZonedDateTime

data class HomeState(
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = emptyList(),
)
