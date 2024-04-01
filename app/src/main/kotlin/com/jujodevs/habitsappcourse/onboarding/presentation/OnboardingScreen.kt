package com.jujodevs.habitsappcourse.onboarding.presentation

import androidx.compose.runtime.Composable
import com.jujodevs.habitsappcourse.R
import com.jujodevs.habitsappcourse.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingPagerInformation(
            title = "Welcome to\nMonumental Habits",
            subtitle = "We can help you to be a better version of youself",
            image = R.drawable.onboarding1
        ),
        OnboardingPagerInformation(
            title = "Create new\nhabits easily",
            subtitle = "We can help you to be a better version of yourself.",
            image = R.drawable.onboarding2
        ),
        OnboardingPagerInformation(
            title = "Keep track of your\nprogress",
            subtitle = "We can help you to be a better version of yourself.",
            image = R.drawable.onboarding3
        ),
        OnboardingPagerInformation(
            title = "Join a supportive\ncommunity",
            subtitle = "We can help you to be a better version of yourself.",
            image = R.drawable.onboarding4
        )
    )

    OnboardingPager(pages = pages, onFinish = onFinish)
}