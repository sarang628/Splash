package com.sryang.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.ar.core.Config
import com.sryang.splash.compose.SplashScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MyComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            SplashScreen(onLoginState = {})
        }

        //composeTestRule.onNodeWithText("Continue").performClick()

        //composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }
}