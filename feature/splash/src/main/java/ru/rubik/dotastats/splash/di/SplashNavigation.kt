package ru.rubik.dotastats.splash.di

import android.app.Activity

interface SplashNavigation {

    fun changeStartDestinationByIsSignedIn(activity: Activity, isSignedIn: Boolean)
}