package com.jujodevs.habitsappcourse.home.api.domain.alarm

import com.jujodevs.habitsappcourse.home.api.domain.models.Habit

interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}