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

    @Component.Builder
    interface Builder {

        fun build(): HeroesLoreComponent
        fun create(dependencies: HeroesLoreExternalDependencies): Builder
    }

    fun inject(heroesLoreFragment: HeroLoreFragment)
}