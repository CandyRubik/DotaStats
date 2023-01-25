package ru.rubik.dotastats

import android.app.Application
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalServiceLocator.initializeContext(this)
    }
}