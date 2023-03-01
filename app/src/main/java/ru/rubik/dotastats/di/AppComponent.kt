package ru.rubik.dotastats.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.rubik.dotastats.details.di.EditNoteExternalDependencies
import ru.rubik.dotastats.details.di.HeroesLoreExternalDependencies
import ru.rubik.dotastats.heroes.all.di.HeroesExternalDependencies
import ru.rubik.dotastats.login.di.LoginExternalDependencies
import ru.rubik.dotastats.notes.all.di.NotesExternalDependencies
import ru.rubik.dotastats.profile.di.ProfileExternalDependencies
import ru.rubik.dotastats.root.RootActivity
import ru.rubik.dotastats.settings.di.SettingsExternalDependencies
import ru.rubik.dotastats.splash.di.SplashExternalDependencies

@AppScope
@Component(
    modules = [
        CommonModule::class,
        NetworkProvidesModule::class,
        NavigationModule::class,
        FeatureExternalDependenciesModule::class
    ]
)
interface AppComponent :
    SplashExternalDependencies,
    LoginExternalDependencies,
    ProfileExternalDependencies,
    SettingsExternalDependencies,
    HeroesExternalDependencies,
    HeroesLoreExternalDependencies,
    NotesExternalDependencies,
    EditNoteExternalDependencies {

    fun inject(appActivity: RootActivity)

    @Component.Factory
    interface Factory {

        fun context(@BindsInstance context: Context): AppComponent
    }
}



