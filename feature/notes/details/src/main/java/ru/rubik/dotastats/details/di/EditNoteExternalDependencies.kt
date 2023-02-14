package ru.rubik.dotastats.details.di

import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.notes_api.domain.repository.NotesRepository
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

interface EditNoteExternalDependencies : FeatureExternalDependencies {

    val repository: NotesRepository
    val profileIdUseCase: ProfileIdUseCase
}