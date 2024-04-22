package com.jujodevs.habitsappcourse.home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujodevs.habitsappcourse.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private var currentDayJob: Job? = null

    init {
        getHabits()
        homeUseCases.syncHabitsUseCase()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeDate -> {
                state = state.copy(selectedDate = event.date)
                getHabits()
            }

            is HomeEvent.CompleteHabit -> {
                viewModelScope.launch {
                    homeUseCases.completeHabitUseCase(
                        habit = event.habit,
                        date = state.selectedDate
                    )
                }
            }
        }
    }

    private fun getHabits() {
        currentDayJob?.cancel()
        currentDayJob = viewModelScope.launch {
            homeUseCases.getHabitsForSelectedUseCase(state.selectedDate).collectLatest {
                state = state.copy(habits = it)
            }
        }
    }
}