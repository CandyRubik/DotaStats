package ru.rubik.dotastats.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.details.presentation.state.EditNoteContentState
import ru.rubik.dotastats.details.presentation.state.EditNoteUiState
import ru.rubik.dotastats.notes_api.domain.models.Note
import ru.rubik.dotastats.notes_api.domain.repository.NotesRepository
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

class EditNoteViewModel(
    note: Note?,
    private val repository: NotesRepository,
    private val profileIdUseCase: ProfileIdUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<EditNoteUiState> = MutableStateFlow(
        EditNoteUiState(
            title = "",
            description = "",
            note = note,
            editNoteContentState = EditNoteContentState.Restore(
                title = note?.title,
                description = note?.description,
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    val isDeleteButtonEnabled = _uiState.map {
        it.note != null
    }

    val isSaveButtonEnabled = _uiState.map {
        it.title.isNotBlank() && it.description.isNotBlank()
    }

    fun onTitleChanged(text: String) {
        _uiState.update {
            it.copy(
                title = text,
                editNoteContentState = EditNoteContentState.Input,
            )
        }
    }

    fun onDescriptionChanged(text: String) {
        _uiState.update {
            it.copy(
                description = text,
                editNoteContentState = EditNoteContentState.Input,
            )
        }
    }

    fun onSaveButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val profileId = requireNotNull(profileIdUseCase.getSteamId()) {
                throw IllegalStateException("steamId can't be null because screen is not from login flow")
            }

            val idNewNote = repository.insertNote(
                Note(
                    _uiState.value.note?.id ?: 0,
                    profileId = profileId,
                    title = _uiState.value.title,
                    description = _uiState.value.description,
                )
            )

            _uiState.update {
                it.copy(
                    note = repository.findNoteByPrimaryKey(idNewNote)
                )
            }
        }
    }

    fun onRemoveButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.note?.let { repository.deleteNotes(it) }
            _uiState.update {
                it.copy(
                    note = null,
                )
            }
        }
    }
}