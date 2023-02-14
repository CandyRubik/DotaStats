package ru.rubik.dotastats.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.rubik.dotastats.notes_api.domain.models.Note
import ru.rubik.dotastats.notes_api.domain.repository.NotesRepository
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

class EditNoteViewModelFactory @AssistedInject constructor(
    @Assisted("note") private val note: Note?,
    private val profileIdUseCase: ProfileIdUseCase,
    private val notesRepository: NotesRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        assert(modelClass == EditNoteViewModel::class.java)
        return EditNoteViewModel(
            note,
            notesRepository,
            profileIdUseCase,
        ) as T
    }
}

@AssistedFactory
interface EditNoteViewModelAssistedFactory {

    fun create(@Assisted("note") note: Note?): EditNoteViewModelFactory
}