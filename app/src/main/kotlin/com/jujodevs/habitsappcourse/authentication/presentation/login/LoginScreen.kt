package com.jujodevs.habitsappcourse.authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujodevs.habitsappcourse.R
import com.jujodevs.habitsappcourse.authentication.presentation.login.components.LoginForm
import com.jujodevs.habitsappcourse.core.presentation.HabitTitle

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LaunchedEffect(state.isLoggedIn, state.signup) {
        when {
            state.isLoggedIn -> onLogin()
            state.signup -> {
                onSignUp()
                viewModel.onEvent(LoginEvent.SignupDone)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .graphicsLayer(
                    scaleX = 1.27f,
                    scaleY = 1.27f
                )
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,

                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier)
            Spacer(modifier = Modifier)
            HabitTitle(
                title = "Welcome To\nMonumental Habits",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LoginForm(state, viewModel::onEvent)
        }
    }
}
