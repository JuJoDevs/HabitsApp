package com.jujodevs.habitsappcourse.home.domain.home.usecase

import com.jujodevs.habitsappcourse.home.domain.models.Habit
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime
import javax.inject.Inject

class GetHabitsForSelectedUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date).map { habits ->
            habits.filter { it.frequency.contains(date.dayOfWeek) }
        }.distinctUntilChanged()
    }
}