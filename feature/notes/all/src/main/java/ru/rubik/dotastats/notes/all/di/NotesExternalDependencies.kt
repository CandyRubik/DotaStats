package ru.rubik.dotastats.notes.all.di

import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.notes_api.domain.repository.NotesRepository
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

interface NotesExternalDependencies : FeatureExternalDependencies {

    val notesNavigation: NotesNavigation
    val profileIdUseCase: ProfileIdUseCase
    val notesRepository: NotesRepository
}