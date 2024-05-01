package com.jujodevs.habitsappcourse.home.api.data.mapper

import com.jujodevs.habitsappcourse.home.api.data.extension.toStartOfDateTimestamp
import com.jujodevs.habitsappcourse.home.api.data.extension.toTimeStamp
import com.jujodevs.habitsappcourse.home.api.data.extension.toZoneDateTime
import com.jujodevs.habitsappcourse.home.api.data.extension.toZonedDateTime
import com.jujodevs.habitsappcourse.home.api.data.local.entity.HabitEntity
import com.jujodevs.habitsappcourse.home.api.data.local.entity.HabitSyncEntity
import com.jujodevs.habitsappcourse.home.api.data.remote.dto.HabitDto
import com.jujodevs.habitsappcourse.home.api.data.remote.dto.HabitResponse
import com.jujodevs.habitsappcourse.home.api.domain.models.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit =
    Habit(
        id = id,
        name = name,
        frequency = frequency.map { DayOfWeek.of(it) },
        completedDates = completedDates.map { it.toZoneDateTime().toLocalDate() },
        reminder = reminder.toZoneDateTime().toLocalTime(),
        startDate = startDate.toZoneDateTime()
    )

fun List<HabitEntity>.toDomain(): List<Habit> = map { it.toDomain() }

fun Habit.toEntity(): HabitEntity =
    HabitEntity(
        id = id,
        name = name,
        frequency = frequency.map { it.value },
        completedDates = completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = reminder.toZonedDateTime().toTimeStamp(),
        startDate = startDate.toStartOfDateTimestamp()
    )

fun HabitResponse.toDomain(): List<Habit> =
    this.map { map ->
        val id = map.key
        val dto = map.value
        Habit(
            id = id,
            name = dto.name,
            frequency = dto.frequency.map { DayOfWeek.of(it) },
            completedDates = dto.completedDates?.map {
                it.toZoneDateTime().toLocalDate()
            } ?: emptyList(),
            reminder = dto.reminder.toZoneDateTime().toLocalTime(),
            startDate = dto.startDate.toZoneDateTime()
        )
    }

fun Habit.toDto(): HabitResponse {
    val dto = HabitDto(
        name = name,
        frequency = frequency.map { it.value },
        completedDates = completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = reminder.toZonedDateTime().toTimeStamp(),
        startDate = startDate.toStartOfDateTimestamp()
    )
    return mapOf(id to dto)
}

fun Habit.toSyncEntity(): HabitSyncEntity =
    HabitSyncEntity(id = id)
