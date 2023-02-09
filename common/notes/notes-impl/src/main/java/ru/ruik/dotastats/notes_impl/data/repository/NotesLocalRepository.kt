package ru.ruik.dotastats.notes_impl.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.rubik.dotastats.notes_api.domain.models.Note
import ru.rubik.dotastats.notes_api.domain.repository.NotesRepository
import ru.ruik.dotastats.notes_impl.data.dao.NotesDao
import ru.ruik.dotastats.notes_impl.data.mappers.NotesMapper
import javax.inject.Inject

class NotesLocalRepository @Inject constructor(
    private val notesDao: NotesDao,
    private val mapper: NotesMapper,
) : NotesRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map { notes ->
            notes.map(mapper::mapToNote)
        }
    }

    override fun findNotesBySteamId(steamId: String): Flow<List<Note>> {
        return notesDao.findNotesBySteamId(steamId).map { notes ->
            notes.map(mapper::mapToNote)
        }
    }

    override suspend fun findNoteByPrimaryKey(id: Long): Note {
        return mapper.mapToNote(notesDao.findNoteByPrimaryKey(id))
    }

    override suspend fun insertNote(note: Note): Long {
        return notesDao.insertNote(
                note = mapper.mapToNoteDto(note)
            )
    }

    override fun deleteNotes(vararg notes: Note) {
        notesDao.deleteNotes(
            notes = notes.map(mapper::mapToNoteDto).toTypedArray()
        )
    }
}