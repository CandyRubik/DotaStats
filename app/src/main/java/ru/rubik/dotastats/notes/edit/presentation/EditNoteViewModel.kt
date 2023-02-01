package ru.rubik.dotastats.notes.edit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.notes.all.domain.models.Note
import ru.rubik.dotastats.notes.all.domain.repository.NotesRepository
import ru.rubik.dotastats.notes.edit.presentation.state.ContentState
import ru.rubik.dotastats.notes.edit.presentation.state.EditNoteUiState
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator
import ru.rubik.dotastats.shared.steamId.domain.usecase.SteamIdUseCase

class EditNoteViewModel(
    note: Note?,
) : ViewModel() {

    private val repository: NotesRepository = GlobalServiceLocator.provideNotesRepository()
    private val steamIdUseCase: SteamIdUseCase =
        GlobalServiceLocator.provideSteamIdUseCase()

    private val _uiState: MutableStateFlow<EditNoteUiState> = MutableStateFlow(
        EditNoteUiState(
            title = "",
            description = "",
            note = note,
            contentState = ContentState.Restore(
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
                contentState = ContentState.Input,
            )
        }
    }

    fun onDescriptionChanged(text: String) {
        _uiState.update {
            it.copy(
                description = text,
                contentState = ContentState.Input,
            )
        }
    }

    fun onSaveButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val steamId = requireNotNull(steamIdUseCase.getSteamId()) {
                throw IllegalStateException("steamId can't be null because screen is not from login flow")
            }

            val idNewNote = repository.insertNote(
                Note(
                    _uiState.value.note?.id ?: 0,
                    steamId = steamId,
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