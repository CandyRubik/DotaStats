package ru.rubik.dotastats.heroes.all.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.heroes.all.di.DaggerHeroesComponent
import ru.rubik.dotastats.heroes.all.di.HeroesExternalDependencies
import ru.rubik.dotastats.heroes.all.presentation.HeroesFeatureComponentDependenciesProvider.featureDependencies

class HeroesFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerHeroesComponent.builder()
            .create(checkNotNull(featureDependencies)).build()
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object HeroesFeatureComponentDependenciesProvider {

    var featureDependencies: HeroesExternalDependencies? = null
}