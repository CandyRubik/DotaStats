package ru.ruik.dotastats.notes_impl.data.mappers

import ru.rubik.dotastats.notes_api.domain.models.Note
import ru.ruik.dotastats.notes_impl.data.entities.NoteDto
import javax.inject.Inject

class NotesMapper @Inject constructor() {

    fun mapToNote(source: NoteDto): Note {
        return Note(
            id = source.id,
            profileId = source.steamId,
            title = source.title,
            description = source.description
        )
    }

    fun mapToNoteDto(source: Note): NoteDto {
        return NoteDto(
            id = source.id,
            steamId = source.profileId,
            title = source.title,
            description = source.description,
        )
    }
}