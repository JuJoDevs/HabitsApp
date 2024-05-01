package com.jujodevs.habitsappcourse.home.data.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.jujodevs.habitsappcourse.MainActivity
import com.jujodevs.habitsappcourse.core.presentation.R
import com.jujodevs.habitsappcourse.home.data.extension.goAsync
import com.jujodevs.habitsappcourse.home.domain.alarm.AlarmHandler
import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val HABIT_ID = "habit_id"
        const val CHANNEL_ID = "habits_channel"
    }

    @Inject
    lateinit var repository: HomeRepository

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var alarmHandler: AlarmHandler

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) return@goAsync
        val id = intent.getStringExtra(HABIT_ID) ?: return@goAsync
        val habit: Habit = repository.getHabitById(id)
        createNotificationChannel()
        if (!habit.completedDates.contains(LocalDate.now())) {
            showNotification(context, habit)
        }
        alarmHandler.setRecurringAlarm(habit)
    }

    private fun showNotification(context: Context, habit: Habit) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(habit.name)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentIntent(createMainPendingIntent(context))
            .setAutoCancel(true)
            .build()
        notificationManager.notify(habit.id.hashCode(), notification)
    }

    private fun createMainPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Habit Reminder Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Get your habits reminder!"
            notificationManager.createNotificationChannel(channel)
        }
    }
}
