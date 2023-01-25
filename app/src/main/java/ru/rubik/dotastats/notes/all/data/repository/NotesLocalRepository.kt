package ru.rubik.dotastats.notes.all.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.rubik.dotastats.notes.all.data.dao.NotesDao
import ru.rubik.dotastats.notes.all.data.mappers.NotesMapper
import ru.rubik.dotastats.notes.all.domain.models.Note
import ru.rubik.dotastats.notes.all.domain.repository.NotesRepository

class NotesLocalRepository(
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