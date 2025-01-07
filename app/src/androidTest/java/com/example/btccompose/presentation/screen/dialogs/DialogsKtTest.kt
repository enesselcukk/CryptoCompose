package com.example.btccompose.presentation.screen.dialogs

import android.os.Bundle
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class DialogsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoadingDialog_isDisplayed() {
        composeTestRule.setContent {
            LoadingDialog()
        }

        composeTestRule.onNodeWithTag("loading_indicator").assertExists()

    }

    @Test
    fun testErrorDialog_isDisplayed() {
        var dismissCalled = false
        val testException = Exception("Test error message")

        composeTestRule.setContent {
            ErrorDialog(
                exception = testException,
                onDismiss = { dismissCalled = true }
            )
        }

        composeTestRule.onNodeWithText("Test error message")
            .assertIsDisplayed()
            .assertHasNoClickAction()

        composeTestRule.onNodeWithText("OK")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        assert(dismissCalled) { "Dialog dismiss callback was not called" }
    }

}
