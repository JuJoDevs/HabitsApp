package com.jujodevs.habitsappcourse.home

import android.util.Log
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.jujodevs.habitsappcourse.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@HiltAndroidTest
class CreateHabitE2E {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor()).build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
        hiltRule.inject()
    }

    @Test
    fun createHabit(): Unit = with(composeRule) {
        val habitToCreate = "Vamos al Gym"
        doLogin()
        onNodeWithText("Home").isDisplayed()
        onNodeWithText(habitToCreate).assertDoesNotExist()
        onNodeWithContentDescription("Add a new habit").performClick()
        onNodeWithContentDescription("Enter habit name").performTextReplacement(habitToCreate)
        Espresso.pressBack()
        val today = LocalDate.now().dayOfWeek
        onNodeWithContentDescription(today.name).performClick()
        onNodeWithContentDescription("Create Habit button").performClick()
        onNodeWithText(habitToCreate).isDisplayed()
    }

    private fun doLogin() = with(composeRule) {
        onNodeWithText("Skip").performClick()
        onNodeWithText("Email").performTextReplacement("asd@asd.com")
        onNodeWithText("Password").performTextReplacement("asdASD123")
        onNodeWithText("Login").performClick()
    }
}