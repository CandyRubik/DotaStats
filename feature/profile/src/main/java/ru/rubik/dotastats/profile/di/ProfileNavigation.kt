package ru.rubik.dotastats.profile.di

import android.app.Activity
import androidx.navigation.NavController

interface ProfileNavigation {

    fun navigateToSettings(navController: NavController)

    fun navigateToLogin(activity: Activity)
}