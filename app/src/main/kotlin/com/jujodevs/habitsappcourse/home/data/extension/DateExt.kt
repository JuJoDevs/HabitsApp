package com.jujodevs.habitsappcourse.home.data.extension

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun ZonedDateTime.toStartOfDateTimestamp(): Long =
    truncatedTo(ChronoUnit.DAYS).toEpochSecond() * 1000

fun Long.toZoneDateTime(): ZonedDateTime =
    ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(this),
        ZoneId.systemDefault()
    )

fun ZonedDateTime.toTimeStamp(): Long =
    toInstant().toEpochMilli()

fun LocalDate.toZonedDateTime(): ZonedDateTime =
    atStartOfDay(ZoneId.systemDefault())

fun LocalTime.toZonedDateTime(): ZonedDateTime =
    atDate(LocalDate.now()).atZone(ZoneId.systemDefault())
