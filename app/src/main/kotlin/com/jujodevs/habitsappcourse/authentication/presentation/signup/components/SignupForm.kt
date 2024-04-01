package com.jujodevs.habitsappcourse.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jujodevs.habitsappcourse.authentication.presentation.signup.SignupEvent
import com.jujodevs.habitsappcourse.authentication.presentation.signup.SignupState
import com.jujodevs.habitsappcourse.core.presentation.HabitButton
import com.jujodevs.habitsappcourse.core.presentation.HabitPasswordTextField
import com.jujodevs.habitsappcourse.core.presentation.HabitTextField
import com.jujodevs.habitsappcourse.core.presentation.HabitTitle

@Composable
fun SignupForm(
    state: SignupState,
    onEvent: (SignupEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        HabitTitle(title = "Create your account")
        Spacer(modifier = Modifier.height(32.dp))
        HabitTextField(
            value = state.email,
            onValueChange = { onEvent(SignupEvent.EmailChange(it)) },
            placeholder = "Email",
            contentDescription = "Enter Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, start = 20.dp, end = 20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            errorMessage = state.emailError,
            isEnabled = !state.isLoading,
            backgroundColor = MaterialTheme.colorScheme.surface
        )
        HabitPasswordTextField(
            value = state.password,
            onValueChange = { onEvent(SignupEvent.PasswordChange(it)) },
            contentDescription = "Enter Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, start = 20.dp, end = 20.dp),
            errorMessage = state.passwordError,
            isEnabled = !state.isLoading,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.clearFocus()
                onEvent(SignupEvent.SignUp)
            }),
            backgroundColor = MaterialTheme.colorScheme.surface
        )
        Spacer(modifier = Modifier.height(12.dp))
        HabitButton(
            text = "Create Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = !state.isLoading,
            onClick = {
                onEvent(SignupEvent.SignUp)
            })
        TextButton(onClick = { onEvent(SignupEvent.SignIn) }) {
            Text(
                text = buildAnnotatedString {
                    append("Already have an account?")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign in")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}
