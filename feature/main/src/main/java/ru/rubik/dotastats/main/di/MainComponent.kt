package ru.rubik.dotastats.main.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.main.presentation.ui.MainFragment

@FeatureScope
@Component(
    dependencies = [MainExternalDependencies::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: MainExternalDependencies): MainComponent
    }

    fun inject(mainFragment: MainFragment)
}