package ru.rubik.dotastats.splash.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.splash.di.DaggerSplashComponent
import ru.rubik.dotastats.splash.di.SplashExternalDependencies
import ru.rubik.dotastats.splash.presentation.SplashFeatureComponentDependenciesProvider.featureDependencies

class SplashFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerSplashComponent.factory()
            .create(checkNotNull(featureDependencies))
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object SplashFeatureComponentDependenciesProvider {

    var featureDependencies: SplashExternalDependencies? = null
}