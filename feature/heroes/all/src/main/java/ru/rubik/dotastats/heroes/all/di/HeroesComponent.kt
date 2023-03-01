package ru.rubik.dotastats.heroes.all.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.heroes.all.presentation.ui.HeroesFragment

@FeatureScope
@Component(
    modules = [HeroesBindsModule::class],
    dependencies = [HeroesExternalDependencies::class]
)
interface HeroesComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: HeroesExternalDependencies): HeroesComponent
    }

    fun inject(heroesFragment: HeroesFragment)
}