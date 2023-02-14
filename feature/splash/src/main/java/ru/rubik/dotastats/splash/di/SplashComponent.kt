package ru.rubik.dotastats.splash.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.splash.presentation.ui.SplashFragment

@FeatureScope
@Component(
    modules = [SplashBindsModule::class],
    dependencies = [SplashExternalDependencies::class]
)
interface SplashComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: SplashExternalDependencies): SplashComponent
    }

    fun inject(splashFragment: SplashFragment)
}