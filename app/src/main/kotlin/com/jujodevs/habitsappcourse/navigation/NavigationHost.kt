package com.jujodevs.habitsappcourse.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jujodevs.habitsappcourse.authentication.presentation.login.LoginScreen
import com.jujodevs.habitsappcourse.authentication.presentation.signup.SignupScreen
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
            OnboardingScreen(
                onFinish = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Login.route)
                })
        }
        composable(route = NavigationRoute.Login.route) {
            LoginScreen(onLogin = {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Home.route)
            }, onSignUp = {
                navHostController.navigate(NavigationRoute.Signup.route)
            })
        }
        composable(route = NavigationRoute.Signup.route) {
            SignupScreen()
        }
        composable(route = NavigationRoute.Home.route) {
            Text(text = "Esta es la home")
        }
    }
}