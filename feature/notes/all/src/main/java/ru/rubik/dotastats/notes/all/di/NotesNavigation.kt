package ru.rubik.dotastats.notes.all.di

import androidx.navigation.NavController
import ru.rubik.dotastats.notes_api.domain.models.Note

interface NotesNavigation {

    fun navigateToNoteDetail(note: Note?, navController: NavController)
}