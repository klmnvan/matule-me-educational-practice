package com.example.matuleme

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.matuleme.presentation.MainActivity
import com.example.matuleme.presentation.navigation.Navigation
import com.example.matuleme.presentation.screens.auth.signin.SignIn

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

    @get:Rule
    val composeTestRule = createComposeRule() //с помощью этой штуки мы эммулируем контекст приложения

    @Test
    fun successfulAuthTest() {
        with(composeTestRule) {
            setContent {
                Navigation(rememberNavController())
            }
            onNodeWithTag("testEmail").performTextInput("nesklmnvan@gmail.com")
            onNodeWithTag("testPass").performTextInput("123456")
            onNodeWithText("Войти").performClick()
            waitUntil(5000) {
                onNodeWithText("Home").isDisplayed()
                true
            }
        }
    }

    @Test
    fun noSuccessfulAuthTest() {
        with(composeTestRule) {
            setContent {
                Navigation(rememberNavController())
            }
            onNodeWithTag("testEmail").performTextInput("nesklmnvan@gmail.com")
            onNodeWithTag("testPass").performTextInput("1234567")
            onNodeWithText("Войти").performClick()
            waitUntil(5000) {
                onNodeWithText("Home").isNotDisplayed()
                true
            }
        }
    }

    @Test
    fun emailIsUncorrect() {
        with(composeTestRule) {
            setContent {
                Navigation(rememberNavController())
            }
            onNodeWithTag("testEmail").performTextInput("test")
            onNodeWithTag("testPass").performTextInput("1")
            onNodeWithText("Войти").performClick()
            onNodeWithText("Неверный формат почты").isDisplayed()
        }
    }

}