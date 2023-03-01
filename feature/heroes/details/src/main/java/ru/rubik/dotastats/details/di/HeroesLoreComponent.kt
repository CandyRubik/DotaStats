package ru.rubik.dotastats.details.di

import dagger.Component
import ru.rubik.dotastats.details.presentation.ui.HeroLoreFragment
import ru.rubik.dotastats.di.FeatureScope

@FeatureScope
@Component(
    modules = [HeroesLoreModule::class],
    dependencies = [HeroesLoreExternalDependencies::class]
)
interface HeroesLoreComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: HeroesLoreExternalDependencies): HeroesLoreComponent
    }

    fun inject(heroesLoreFragment: HeroLoreFragment)
}