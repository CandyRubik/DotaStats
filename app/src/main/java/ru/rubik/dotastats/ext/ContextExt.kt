package ru.rubik.dotastats.ext

import android.content.Context
import ru.rubik.dotastats.App
import ru.rubik.dotastats.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> component
        else -> (applicationContext as App).component
    }