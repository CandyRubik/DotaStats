package ru.rubik.dotastats.notes.all.presentation.state

import ru.rubik.dotastats.notes_api.domain.models.Note

data class NotesUiState(
    val notes: List<Note> = emptyList()
)