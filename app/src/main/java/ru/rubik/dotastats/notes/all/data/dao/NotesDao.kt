package ru.rubik.dotastats.notes.all.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.rubik.dotastats.notes.all.data.entities.NoteDto

@Dao
interface NotesDao {

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<NoteDto>>

    @Query("SELECT * FROM note_table WHERE steamId IN (:steamId)")
    fun findNotesBySteamId(steamId: String): Flow<List<NoteDto>>

    @Query("SELECT * FROM note_table WHERE id IN (:id)")
    suspend fun findNoteByPrimaryKey(id: Long): NoteDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDto): Long

    @Delete
    fun deleteNotes(vararg notes: NoteDto)
}