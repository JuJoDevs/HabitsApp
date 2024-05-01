package com.jujodevs.habitsappcourse.home.presentation.home

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujodevs.habitsappcourse.core.presentation.R
import com.jujodevs.habitsappcourse.home.presentation.home.components.HomeAskPermission
import com.jujodevs.habitsappcourse.home.presentation.home.components.HomeDateSelector
import com.jujodevs.habitsappcourse.home.presentation.home.components.HomeHabit
import com.jujodevs.habitsappcourse.home.presentation.home.components.HomeQuote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNewHabit: () -> Unit,
    onSettings: () -> Unit,
    onEditHabit: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Home")
            }, navigationIcon = {
                IconButton(onClick = onSettings) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                }
            })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = onNewHabit,
            containerColor = MaterialTheme.colorScheme.primary,
            shape = CircleShape,
            modifier = Modifier.semantics {
                contentDescription = "Add a new habit"
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Habit",
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }) { paddingValues ->

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            HomeAskPermission(permission = Manifest.permission.POST_NOTIFICATIONS)
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 20.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 20.dp)
        ) {
            item {
                HomeQuote(
                    quote = "We first make our habits,\nand then our habits\nmake us.",
                    author = "Anonymous",
                    imageId = R.drawable.onboarding1,
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                ) {
                    Text(
                        text = "Habits".uppercase(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    HomeDateSelector(
                        selectedDate = state.selectedDate,
                        mainDate = state.currentDate,
                        onDateClick = {
                            viewModel.onEvent(HomeEvent.ChangeDate(it))
                        },
                    )
                }
            }
            items(state.habits) {
                HomeHabit(
                    habit = it,
                    selectedDate = state.selectedDate.toLocalDate(),
                    onCheckedChange = { viewModel.onEvent(HomeEvent.CompleteHabit(it)) },
                    onHabitClick = { onEditHabit(it.id) },
                )
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}