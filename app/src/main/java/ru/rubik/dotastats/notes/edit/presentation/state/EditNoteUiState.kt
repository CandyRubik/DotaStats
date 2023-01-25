package ru.rubik.dotastats.notes.edit.presentation.state

import ru.rubik.dotastats.notes.all.domain.models.Note

data class EditNoteUiState(
    val title: String,
    val description: String,
    val note: Note?,
    val contentState: ContentState
)

sealed interface ContentState {
    data class Restore(
        val title: String?,
        val description: String?,
    ): ContentState

    object Input: ContentState
}