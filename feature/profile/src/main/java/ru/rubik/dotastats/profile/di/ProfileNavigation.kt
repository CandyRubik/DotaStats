package ru.rubik.dotastats.profile.di

import androidx.annotation.IdRes

interface ProfileNavigation {

    @get:IdRes
    val actionProfileFragmentToSettingsFragment: Int

    @get:IdRes
    val actionMainFragmentToAuthGraph: Int

    @get:IdRes
    val activityNavHost: Int
}