package com.jujodevs.habitsappcourse.home.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HabitDto(
    val name: String,
    val reminder: Long,
    val startDate: Long,
    val frequency: List<Int>,
    val completedDates: List<Long>?,
)
