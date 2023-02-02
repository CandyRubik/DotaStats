package ru.rubik.dotastats.notes.all.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteDto(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val steamId: String,
    val title: String,
    val description: String
)