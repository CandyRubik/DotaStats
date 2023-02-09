package ru.rubik.dotastats.main.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.main.di.DaggerMainComponent
import ru.rubik.dotastats.main.di.MainExternalDependencies
import ru.rubik.dotastats.main.presentation.MainFeatureComponentDependenciesProvider.featureDependencies

class MainFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerMainComponent.factory()
            .create(checkNotNull(featureDependencies))
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object MainFeatureComponentDependenciesProvider {

    var featureDependencies: MainExternalDependencies? = null
}