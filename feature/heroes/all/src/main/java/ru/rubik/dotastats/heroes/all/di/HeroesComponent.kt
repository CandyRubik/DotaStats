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

    @Component.Builder
    interface Builder {

        fun build(): HeroesComponent
        fun create(dependencies: HeroesExternalDependencies): Builder
    }

    fun inject(heroesFragment: HeroesFragment)
}