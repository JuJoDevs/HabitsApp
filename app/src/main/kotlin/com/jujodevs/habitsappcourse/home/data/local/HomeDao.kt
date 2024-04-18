package com.jujodevs.habitsappcourse.home.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jujodevs.habitsappcourse.home.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
    /*fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>>
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(habitId: String): Habit*/

    @Upsert
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Query("SELECT * FROM HabitEntity WHERE id = :id")
    suspend fun getHabitById(id: String): HabitEntity?

    @Query("SELECT * FROM HabitEntity WHERE startDate <= :date ORDER BY id ASC")
    fun getAllHabitsForSelectedDate(date: Long): Flow<List<HabitEntity>>

}