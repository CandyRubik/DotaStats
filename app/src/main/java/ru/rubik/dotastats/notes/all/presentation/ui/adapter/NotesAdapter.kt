package ru.rubik.dotastats.notes.all.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.ItemNoteBinding
import ru.rubik.dotastats.notes.all.domain.models.Note
import ru.rubik.dotastats.profile.presentation.ui.adapter.RecentPlayedMatchViewHolder

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
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        holder.bind(getItem(position))
    }
}