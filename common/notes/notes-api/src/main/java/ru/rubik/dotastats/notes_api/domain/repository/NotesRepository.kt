package ru.rubik.dotastats.notes_api.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.rubik.dotastats.notes_api.domain.models.Note

interface NotesRepository {

    fun getAllNotes(): Flow<List<Note>>
    fun findNotesBySteamId(steamId: String): Flow<List<Note>>
    suspend fun findNoteByPrimaryKey(id: Long): Note
    suspend fun insertNote(note: Note): Long
    fun deleteNotes(vararg notes: Note)
}