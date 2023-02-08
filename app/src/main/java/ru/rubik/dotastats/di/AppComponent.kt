package ru.rubik.dotastats.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.rubik.dotastats.heroes.di.HeroesModule
import ru.rubik.dotastats.login.di.LoginExternalDependencies
import ru.rubik.dotastats.night_mode.di.NightModeModule
import ru.rubik.dotastats.profile.di.ProfileExternalDependencies
import ru.rubik.dotastats.profile_id.di.ProfileIdModule
import ru.rubik.dotastats.profile_impl.di.ProfileModule
import ru.rubik.dotastats.settings.di.SettingsExternalDependencies
import ru.rubik.dotastats.splash.di.SplashExternalDependencies

@AppScope
@Component(
    modules = [HeroesModule::class, NightModeModule::class, ProfileIdModule::class, NetworkProvidesModule::class, ProfileModule::class]
)
interface AppComponent :
    SplashExternalDependencies,
    SettingsExternalDependencies,
    ProfileExternalDependencies,
    LoginExternalDependencies {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}


