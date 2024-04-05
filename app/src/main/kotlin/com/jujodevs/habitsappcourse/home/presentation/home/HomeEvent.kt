package com.jujodevs.habitsappcourse.home.presentation.home

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import java.time.ZonedDateTime

sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime) : HomeEvent
    data class CompleteHabit(val habit: Habit) : HomeEvent
}
