package ru.rubik.dotastats.details.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.details.di.DaggerHeroesLoreComponent
import ru.rubik.dotastats.details.di.HeroesLoreExternalDependencies
import ru.rubik.dotastats.details.presentation.HeroesLoreFeatureComponentDependenciesProvider.featureDependencies

class HeroesLoreFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerHeroesLoreComponent.factory()
            .create(checkNotNull(featureDependencies))
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object HeroesLoreFeatureComponentDependenciesProvider {

    var featureDependencies: HeroesLoreExternalDependencies? = null
}