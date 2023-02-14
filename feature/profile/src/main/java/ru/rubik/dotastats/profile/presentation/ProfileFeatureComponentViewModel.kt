package ru.rubik.dotastats.profile.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.profile.di.DaggerProfileComponent
import ru.rubik.dotastats.profile.di.ProfileExternalDependencies
import ru.rubik.dotastats.profile.presentation.ProfileFeatureComponentDependenciesProvider.featureDependencies

class ProfileFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerProfileComponent.factory()
            .create(checkNotNull(featureDependencies))
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object ProfileFeatureComponentDependenciesProvider {

    var featureDependencies: ProfileExternalDependencies? = null
}