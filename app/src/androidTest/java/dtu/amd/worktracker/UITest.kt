package dtu.amd.worktracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dtu.amd.worktracker.component.CustomDropdown
import dtu.amd.worktracker.ui.theme.WorktrackerTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UITest {

    // Through this rule you can set Compose content or access the activity
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCustomDropdown() {

        val label = mutableStateOf("0")

        // Start the app
        composeTestRule.setContent {
            WorktrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CustomDropdown(label = label.value, selectedIndex = 0, options = listOf("0", "1", "2", "3"), onChange = {label.value = it})
                }
            }
        }

        composeTestRule.onNodeWithTag("Expand").performClick()

        composeTestRule.onNodeWithText("1").performClick()

        composeTestRule.onNodeWithTag("TextField").assertTextContains("1")
    }
}