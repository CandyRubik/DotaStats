package ru.rubik.dotastats

import android.app.Application
import ru.rubik.dotastats.di.AppComponent
import ru.rubik.dotastats.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().context(this).build()
    }
}
