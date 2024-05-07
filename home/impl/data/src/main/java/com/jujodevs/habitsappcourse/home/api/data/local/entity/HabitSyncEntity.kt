package com.jujodevs.habitsappcourse.home.api.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HabitSyncEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
)