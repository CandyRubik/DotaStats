package ru.rubik.dotastats.main.di

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes

interface MainNavigation {

    @get:NavigationRes
    val heroesGraphResource: Int

    @get:NavigationRes
    val profileGraphResource: Int

    @get:NavigationRes
    val notesGraphResource: Int

    @get:IdRes
    val activityNavHost: Int
}