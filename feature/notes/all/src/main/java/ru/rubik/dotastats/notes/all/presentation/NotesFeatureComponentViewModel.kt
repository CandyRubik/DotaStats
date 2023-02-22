package ru.rubik.dotastats.notes.all.presentation

import androidx.lifecycle.ViewModel
import ru.rubik.dotastats.notes.all.di.DaggerNotesComponent
import ru.rubik.dotastats.notes.all.di.NotesExternalDependencies
import ru.rubik.dotastats.notes.all.presentation.NotesFeatureComponentDependenciesProvider.featureDependencies

class NotesFeatureComponentViewModel : ViewModel() {

    val component by lazy {
        DaggerNotesComponent.builder()
            .create(checkNotNull(featureDependencies)).build()
    }

    override fun onCleared() {
        super.onCleared()
        featureDependencies = null
    }
}

object NotesFeatureComponentDependenciesProvider {

    var featureDependencies: NotesExternalDependencies? = null
}