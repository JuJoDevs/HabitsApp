package com.jujodevs.habitsappcourse.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jujodevs.habitsappcourse.R
import com.jujodevs.habitsappcourse.authentication.presentation.signup.components.SignupForm

@Composable
fun SignupScreen(
    onSignIn: () -> Unit,
    onLogIn: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LaunchedEffect(state.isSignedIn, state.logIn) {
        when {
            state.isSignedIn -> onSignIn()
            state.logIn -> onLogIn()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(painter = painterResource(id = R.drawable.signup), contentDescription = null)
            SignupForm(state = state, onEvent = viewModel::onEvent)
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}