package ru.rubik.dotastats.details.presentation.state

import ru.rubik.dotastats.notes_api.domain.models.Note

data class EditNoteUiState(
    val title: String,
    val description: String,
    val note: Note?,
    val editNoteContentState: EditNoteContentState
)

sealed interface EditNoteContentState {
    data class Restore(
        val title: String?,
        val description: String?,
    ) : EditNoteContentState

    object Input : EditNoteContentState
}