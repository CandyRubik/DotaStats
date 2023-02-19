package ru.rubik.dotastats

import android.app.Application
import com.google.firebase.FirebaseApp
import ru.rubik.dotastats.di.AppComponent
import ru.rubik.dotastats.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
        component = DaggerAppComponent.builder().context(this).build()
    }
}
