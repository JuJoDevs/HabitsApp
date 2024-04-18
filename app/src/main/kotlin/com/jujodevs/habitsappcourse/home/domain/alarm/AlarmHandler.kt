package com.jujodevs.habitsappcourse.home.domain.alarm

import com.jujodevs.habitsappcourse.home.domain.models.Habit

interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}