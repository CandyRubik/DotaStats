package ru.rubik.dotastats.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.details.di.EditNoteExternalDependencies
import ru.rubik.dotastats.details.di.HeroesLoreExternalDependencies
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.di.dependency.FeatureExternalDependenciesKey
import ru.rubik.dotastats.heroes.all.di.HeroesExternalDependencies
import ru.rubik.dotastats.login.di.LoginExternalDependencies
import ru.rubik.dotastats.main.di.MainExternalDependencies
import ru.rubik.dotastats.notes.all.di.NotesExternalDependencies
import ru.rubik.dotastats.profile.di.ProfileExternalDependencies
import ru.rubik.dotastats.settings.di.SettingsExternalDependencies
import ru.rubik.dotastats.splash.di.SplashExternalDependencies

@Module
interface FeatureExternalDependenciesModule {

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(SplashExternalDependencies::class)
    fun bindSplashExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(LoginExternalDependencies::class)
    fun bindLoginExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(ProfileExternalDependencies::class)
    fun bindProfileExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(SettingsExternalDependencies::class)
    fun bindSettingsExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(HeroesExternalDependencies::class)
    fun bindHeroesExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(HeroesLoreExternalDependencies::class)
    fun bindHeroesLoreExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(NotesExternalDependencies::class)
    fun bindNotesExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(EditNoteExternalDependencies::class)
    fun bindEditNoteExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies

    @Binds
    @IntoMap
    @FeatureExternalDependenciesKey(MainExternalDependencies::class)
    fun bindMainExternalDependencies(appComponent: AppComponent): FeatureExternalDependencies
}