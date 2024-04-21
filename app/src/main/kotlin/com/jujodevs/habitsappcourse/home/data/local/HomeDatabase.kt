package com.jujodevs.habitsappcourse.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jujodevs.habitsappcourse.home.data.local.entity.HabitEntity
import com.jujodevs.habitsappcourse.home.data.local.entity.HabitSyncEntity
import com.jujodevs.habitsappcourse.home.data.local.typeconverter.HomeTypeConverter

@Database(
    entities = [
        HabitEntity::class,
        HabitSyncEntity::class
    ],
    version = 1
)
@TypeConverters(HomeTypeConverter::class)
abstract class HomeDatabase : RoomDatabase() {
    abstract fun habitDao(): HomeDao
}