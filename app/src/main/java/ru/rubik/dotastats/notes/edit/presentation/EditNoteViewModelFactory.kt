package ru.rubik.dotastats.notes.edit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.notes.all.domain.models.Note

class EditNoteViewModelFactory(private val note: Note?) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(
                note = note
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}