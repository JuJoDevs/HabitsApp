package com.jujodevs.habitsappcourse.home.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujodevs.core.presentation.component.HabitTextField
import com.jujodevs.habitsappcourse.home.presentation.detail.components.DetailFrequency
import com.jujodevs.habitsappcourse.home.presentation.detail.components.DetailReminder
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBack: () -> Unit,
    onSave: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) onSave()
    }

    val clockState = rememberSheetState()
    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            viewModel.onEvent(DetailEvent.ReminderChange(LocalTime.of(hours, minutes)))
        }, config = ClockConfig(
            defaultTime = state.reminder,
            is24HourFormat = true,
        )
    )

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "New Habit")
            }, navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.onEvent(DetailEvent.HabitSave) },
            containerColor = MaterialTheme.colorScheme.primary,
            shape = CircleShape,
            modifier = Modifier.semantics {
                contentDescription = "Create Habit button"
            }
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Create Habit",
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            HabitTextField(
                value = state.habitName,
                onValueChange = { viewModel.onEvent(DetailEvent.NameChange(it)) },
                placeholder = "New Habit",
                contentDescription = "Enter habit name",
                backgroundColor = Color.White,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        viewModel.onEvent(DetailEvent.HabitSave)
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            DetailFrequency(
                selectedDays = state.frequency,
                onFrequencyChange = {
                    viewModel.onEvent(DetailEvent.FrequencyChange(it))
                })
            DetailReminder(
                reminder = state.reminder,
                onTimeClick = { clockState.show() }
            )
        }
    }
}