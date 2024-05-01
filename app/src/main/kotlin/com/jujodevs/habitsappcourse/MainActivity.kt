package com.jujodevs.habitsappcourse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jujodevs.habitsappcourse.core.presentation.theme.HabitsAppCourseTheme
import com.jujodevs.habitsappcourse.home.api.data.alarm.NotificationNavigationHandler
import com.jujodevs.habitsappcourse.navigation.NavigationHost
import com.jujodevs.habitsappcourse.navigation.NavigationRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity(), NotificationNavigationHandler {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsAppCourseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination(),
                        logout = { viewModel.logout() }
                    )
                }
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {
        return when {
            viewModel.isLoggedIn ->
                NavigationRoute.Home

            viewModel.hasSeenOnboarding ->
                NavigationRoute.Login

            else ->
                NavigationRoute.Onboarding
        }
    }

    override fun createMainPendingIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}