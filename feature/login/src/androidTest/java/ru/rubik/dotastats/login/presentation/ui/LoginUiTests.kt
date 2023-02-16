package ru.rubik.dotastats.login.presentation.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import com.agoda.kakao.screen.Screen.Companion.onScreen
import org.junit.Test

class LoginUiTests {

    private val screen = LoginScreen()

    @Test
    fun someName() {

        val scenario = launchFragmentInContainer<LoginFragment>()

        onScreen<LoginScreen> {
            button.isVisible()
        }
    }
}