package ru.rubik.dotastats.login.presentation.ui

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import ru.rubik.dotastats.login.R

class LoginScreen : Screen<LoginScreen>() {

    val button = KButton { withId(R.id.login_button) }
}