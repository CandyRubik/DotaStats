package ru.rubik.dotastats

import android.app.Application
import android.content.Context
import ru.rubik.dotastats.di.AppComponent
import ru.rubik.dotastats.di.DaggerAppComponent
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.factory().create(this)

        GlobalServiceLocator.initializeContext(this)
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> component
        else -> (applicationContext as App).component
    }
