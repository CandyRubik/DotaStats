package ru.rubik.dotastats.notes.all.presentation.state

import ru.rubik.dotastats.notes.all.domain.models.Note

data class NotesUiState(
    val notes: List<Note> = emptyList()
)