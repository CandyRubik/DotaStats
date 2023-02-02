package ru.rubik.dotastats.notes.all.data.mappers

import ru.rubik.dotastats.notes.all.data.entities.NoteDto
import ru.rubik.dotastats.notes.all.domain.models.Note

class NotesMapper {

    fun mapToNote(source: NoteDto): Note {
        return Note(
            id = source.id,
            steamId = source.steamId,
            title = source.title,
            description = source.description
        )
    }

    fun mapToNoteDto(source: Note): NoteDto {
        return NoteDto(
            id = source.id,
            steamId = source.steamId,
            title = source.title,
            description = source.description,
        )
    }
}