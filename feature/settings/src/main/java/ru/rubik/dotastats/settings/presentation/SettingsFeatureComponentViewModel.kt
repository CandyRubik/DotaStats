package ru.rubik.dotastats.settings.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.settings.di.DaggerSettingsComponent
import ru.rubik.dotastats.settings.di.SettingsExternalDependencies
import ru.rubik.dotastats.settings.presentation.SettingsFeatureComponentDependenciesProvider.featureDependencies

class SettingsFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerSettingsComponent.builder()
            .create(checkNotNull(featureDependencies)).build()
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object SettingsFeatureComponentDependenciesProvider {

    var featureDependencies: SettingsExternalDependencies? = null
}