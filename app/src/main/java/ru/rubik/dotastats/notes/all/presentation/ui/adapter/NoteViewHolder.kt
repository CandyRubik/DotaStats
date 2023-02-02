package ru.rubik.dotastats.notes.all.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.rubik.dotastats.databinding.ItemNoteBinding
import ru.rubik.dotastats.notes.all.domain.models.Note

class NoteViewHolder(
    private val binding: ItemNoteBinding,
    private val onItemClickListener: (Note) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Note) {
        with(binding) {
            root.setOnClickListener {
                onItemClickListener(data)
            }
            title.text = data.title
            description.text = data.description
        }
    }
}