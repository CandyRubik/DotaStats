package ru.rubik.dotastats.notes.all.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.notes.all.domain.repository.NotesRepository
import ru.rubik.dotastats.notes.all.presentation.state.NotesUiState
import ru.rubik.dotastats.profile_id.domain.usecase.ProfileIdUseCase
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator

class NotesViewModel : ViewModel() {

    private val repository: NotesRepository = GlobalServiceLocator.provideNotesRepository()
    private val profileIdUseCase: ProfileIdUseCase =
        GlobalServiceLocator.provideProfileIdUseCase()

    private val _uiState: MutableStateFlow<NotesUiState> = MutableStateFlow(
        NotesUiState()
    )
    val uiState = _uiState.asStateFlow()

    init {
        collectNotesBySteamId()
    }

    private fun collectNotesBySteamId() {
        viewModelScope.launch(Dispatchers.IO) {
            val steamId = requireNotNull(profileIdUseCase.getSteamId()) {
                throw IllegalStateException("steamId can't be null because screen is not from login flow")
            }
            repository.findNotesBySteamId(steamId).collect { notes ->
                _uiState.update {
                    it.copy(
                        notes = notes
                    )
                }
            }
        }
    }
}