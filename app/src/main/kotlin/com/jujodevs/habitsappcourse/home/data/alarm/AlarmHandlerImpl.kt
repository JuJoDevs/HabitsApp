package com.jujodevs.habitsappcourse.home.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.jujodevs.habitsappcourse.home.data.alarm.AlarmReceiver.Companion.HABIT_ID
import com.jujodevs.habitsappcourse.home.data.extension.toTimeStamp
import com.jujodevs.habitsappcourse.home.domain.alarm.AlarmHandler
import com.jujodevs.habitsappcourse.home.domain.models.Habit
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.DayOfWeek
import java.time.ZonedDateTime
import javax.inject.Inject

class AlarmHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val alarmManager: AlarmManager,
) : AlarmHandler {
    override fun setRecurringAlarm(habit: Habit) {
        val nextOcurrance = calculateNextOcurrance(habit)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextOcurrance.toTimeStamp(),
            createPendingIntent(habit, nextOcurrance.dayOfWeek)
        )
    }

    private fun createPendingIntent(habit: Habit, dayOfWeek: DayOfWeek): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(HABIT_ID, habit.id)
        }
        return PendingIntent.getBroadcast(
            context,
            habit.id.hashCode() * 10 + dayOfWeek.value,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun calculateNextOcurrance(habit: Habit): ZonedDateTime {
        val today = ZonedDateTime.now()
        var nextOcurrance = ZonedDateTime.of(today.toLocalDate(), habit.reminder, today.zone)

        if (habit.frequency.contains(today.dayOfWeek) && today.isBefore(nextOcurrance)) {
            return nextOcurrance
        }

        do {
            nextOcurrance = nextOcurrance.plusDays(1)
        } while (!habit.frequency.contains(nextOcurrance.dayOfWeek))

        return nextOcurrance
    }

    override fun cancel(habit: Habit) {
        val nextOcurrance = calculateNextOcurrance(habit)
        val pendingIntent = createPendingIntent(habit, nextOcurrance.dayOfWeek)
        alarmManager.cancel(pendingIntent)
    }
}
