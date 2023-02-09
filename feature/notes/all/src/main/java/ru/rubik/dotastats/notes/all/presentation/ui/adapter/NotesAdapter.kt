package ru.rubik.dotastats.notes.all.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.notes.all.databinding.ItemNoteBinding
import ru.rubik.dotastats.notes_api.domain.models.Note

class NotesAdapter(
    diffCallback: DiffUtil.ItemCallback<Note>,
    private val onItemClickListener: (Note) -> Unit,
) : ListAdapter<Note, NoteViewHolder>(diffCallback) {

    var itemsList: List<Note> = listOf()
        set(value) {
            field = value
            submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NoteViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}