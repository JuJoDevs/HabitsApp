package com.jujodevs.habitsappcourse.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jujodevs.habitsappcourse.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(route = NavigationRoute.Onboarding.route) {
            OnboardingScreen(onFinish = {
                println("Terminó el onboarding")
            })
        }
    }
}