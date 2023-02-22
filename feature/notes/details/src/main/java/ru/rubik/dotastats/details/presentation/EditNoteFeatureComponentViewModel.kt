package ru.rubik.dotastats.details.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.details.di.DaggerEditNoteComponent
import ru.rubik.dotastats.details.di.EditNoteExternalDependencies
import ru.rubik.dotastats.details.presentation.EditNoteFeatureComponentDependenciesProvider.featureDependencies

class EditNoteFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerEditNoteComponent.builder()
            .create(checkNotNull(featureDependencies)).build()
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object EditNoteFeatureComponentDependenciesProvider {

    var featureDependencies: EditNoteExternalDependencies? = null
}