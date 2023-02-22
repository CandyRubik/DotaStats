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

    @Component.Builder
    interface Builder {

        fun build(): SplashComponent
        fun create(dependencies: SplashExternalDependencies): Builder
    }

    fun inject(splashFragment: SplashFragment)
}