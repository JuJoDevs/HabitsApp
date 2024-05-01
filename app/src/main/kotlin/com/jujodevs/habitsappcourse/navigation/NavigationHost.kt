package com.jujodevs.habitsappcourse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jujodevs.habitappcourse.api.presentation.SettingsScreen
import com.jujodevs.habitsappcourse.authentication.presentation.login.LoginScreen
import com.jujodevs.habitsappcourse.authentication.presentation.signup.SignupScreen
import com.jujodevs.habitsappcourse.home.presentation.detail.DetailScreen
import com.jujodevs.habitsappcourse.home.presentation.home.HomeScreen
import com.jujodevs.habitsappcourse.onboarding.api.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    logout: () -> Unit,
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
            SignupScreen(
                onSignIn = {
                    navHostController.navigate(NavigationRoute.Home.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                }, onLogIn = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = NavigationRoute.Home.route) {
            HomeScreen(
                onNewHabit = {
                    navHostController.navigate(NavigationRoute.Detail.route)
                },
                onSettings = {
                    navHostController.navigate(NavigationRoute.Settings.route)
                },
                onEditHabit = {
                    navHostController.navigate(NavigationRoute.Detail.createRoute(it))
                }
            )
        }
        composable(
            route = NavigationRoute.Detail.routeWithArguments,
            arguments = NavigationRoute.Detail.arguments
        ) {
            DetailScreen(
                onBack = { navHostController.popBackStack() },
                onSave = { navHostController.popBackStack() }
            )
        }
        composable(route = NavigationRoute.Settings.route) {
            SettingsScreen(
                onBack = { navHostController.popBackStack() },
                onLogoout = {
                    logout()
                    navHostController.navigate(NavigationRoute.Login.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}