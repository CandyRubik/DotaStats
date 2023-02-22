package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.login.di.DaggerLoginComponent
import ru.rubik.dotastats.login.di.LoginExternalDependencies
import ru.rubik.dotastats.login.presentation.LoginFeatureComponentDependenciesProvider.featureDependencies

class LoginFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerLoginComponent.builder()
            .create(checkNotNull(featureDependencies)).build()
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object LoginFeatureComponentDependenciesProvider {

    var featureDependencies: LoginExternalDependencies? = null
}