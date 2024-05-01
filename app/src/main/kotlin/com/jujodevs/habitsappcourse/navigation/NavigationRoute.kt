package com.jujodevs.habitsappcourse.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jujodevs.habitsappcourse.core.domain.HABIT_ID_KEY

sealed class NavigationRoute(val route: String) {
    data object Onboarding : NavigationRoute("onboarding")
    data object Login : NavigationRoute("login")
    data object Signup : NavigationRoute("signup")
    data object Home : NavigationRoute("home")
    data object Detail : NavigationRoute("detail") {
        val arguments = listOf(
            navArgument(HABIT_ID_KEY) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )
        val routeWithArguments = "$route?$HABIT_ID_KEY={$HABIT_ID_KEY}"
        fun createRoute(habitId: String): String = "$route?$HABIT_ID_KEY=$habitId"
    }
    data object Settings : NavigationRoute("settings")
}