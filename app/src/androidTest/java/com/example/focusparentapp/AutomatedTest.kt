package com.example.focusparentapp

import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.swipeLeft
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.focusparentapp.QRscan.QrScanner
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.allOf
@RunWith(AndroidJUnit4::class)
class AutomatedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setupIntents() {
        Intents.init()
    }

    @After
    fun tearDownIntents() {
        Intents.release()
    }

    @Test
    fun testNavigation() {
        composeRule
            .onNodeWithTag("Get Started")
            .assertIsDisplayed()
            .performClick()


        composeRule.onNodeWithTag("TutorialPager").assertIsDisplayed()


        composeRule.onNodeWithTag("TutorialPager").performGesture { swipeLeft() }


        composeRule.onNodeWithTag("Page2").assertIsDisplayed()


        composeRule.onNodeWithTag("TutorialPager").performGesture { swipeLeft() }


        composeRule.onNodeWithTag("Page3").assertIsDisplayed()


        composeRule.onNodeWithTag("TutorialPager").performGesture { swipeLeft() }


        composeRule.onNodeWithTag("Page4").assertIsDisplayed()

        composeRule.onNodeWithText("Start").performClick()

        composeRule.onNodeWithTag("ScanQR").performClick()

        Intents.intended(
            hasComponent(QrScanner::class.java.name)
        )

    }


}