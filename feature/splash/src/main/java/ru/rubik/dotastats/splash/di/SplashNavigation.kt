package ru.rubik.dotastats.splash.di

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes

interface SplashNavigation {

    @get:NavigationRes
    val appGraphResource: Int

    @get:IdRes
    val mainFragmentResource: Int

    @get:IdRes
    val authGraphResource: Int

    @get:IdRes
    val activityNavHost: Int
}