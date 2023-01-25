package ru.rubik.dotastats.notes.all.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentNotesBinding
import ru.rubik.dotastats.notes.all.domain.models.Note
import ru.rubik.dotastats.notes.all.presentation.NotesViewModel
import ru.rubik.dotastats.notes.all.presentation.ui.adapter.NotesAdapter

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val binding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)

    private val viewModel: NotesViewModel by viewModels()

    private val adapter by lazy {
        NotesAdapter(
            diffCallback = object :
                DiffUtil.ItemCallback<Note>() {
                override fun areItemsTheSame(
                    oldItem: Note,
                    newItem: Note
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Note,
                    newItem: Note
                ): Boolean {
                    return oldItem == newItem
                }
            },
            onItemClickListener = {
                val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(
                    note = it
                )
                findNavController().navigate(action)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                adapter.itemsList = it.notes
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(
                note = null
            )
            findNavController().navigate(action)
        }
    }
}