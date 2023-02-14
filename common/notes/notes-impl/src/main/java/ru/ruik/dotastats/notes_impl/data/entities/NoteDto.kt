package ru.ruik.dotastats.notes_impl.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteDto(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val steamId: String,
    val title: String,
    val description: String
)