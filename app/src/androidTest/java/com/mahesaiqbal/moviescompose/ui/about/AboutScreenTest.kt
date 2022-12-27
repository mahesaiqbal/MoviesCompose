package com.mahesaiqbal.moviescompose.ui.about

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.mahesaiqbal.moviescompose.R
import com.mahesaiqbal.moviescompose.ui.theme.MoviesComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MoviesComposeTheme {
                AboutScreen {

                }
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun aboutContent_isDisplayed() {
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.profile_photo))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.profile_name))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.profile_email))
            .assertIsDisplayed()
    }

    @Test
    fun aboutContent_clickBackScreen() {
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.button_back)).performClick()
    }
}