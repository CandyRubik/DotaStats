package ru.rubik.dotastats.login.di

import androidx.annotation.IdRes

interface LoginNavigation {

    @get:IdRes
    val mainFragmentResource: Int

    @get:IdRes
    val authGraphIdResource: Int
}