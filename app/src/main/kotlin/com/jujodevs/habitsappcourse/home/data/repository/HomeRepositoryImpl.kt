package com.jujodevs.habitsappcourse.home.data.repository

import com.jujodevs.habitsappcourse.home.data.extension.toStartOfDateTimestamp
import com.jujodevs.habitsappcourse.home.data.local.HomeDao
import com.jujodevs.habitsappcourse.home.data.mapper.toDomain
import com.jujodevs.habitsappcourse.home.data.mapper.toEntity
import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dao: HomeDao,
) : HomeRepository {
    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp()).map { it.toDomain() }
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
    }

    override suspend fun getHabitById(habitId: String): Habit {
        return dao.getHabitById(habitId).toDomain()
    }
}