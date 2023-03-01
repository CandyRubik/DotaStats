package ru.rubik.dotastats.notes.all.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.di.viewmodel.MultiViewModelFactory
import ru.rubik.dotastats.notes.all.R
import ru.rubik.dotastats.notes.all.databinding.FragmentNotesBinding
import ru.rubik.dotastats.notes.all.di.NotesNavigation
import ru.rubik.dotastats.notes.all.presentation.NotesFeatureComponentDependenciesProvider
import ru.rubik.dotastats.notes.all.presentation.NotesFeatureComponentViewModel
import ru.rubik.dotastats.notes.all.presentation.NotesViewModel
import ru.rubik.dotastats.notes.all.presentation.ui.adapter.NotesAdapter
import ru.rubik.dotastats.notes_api.domain.models.Note
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val binding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: NotesViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var navigation: NotesNavigation

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
                viewModel.navigateToDetail(
                    note = it,
                    navController = findNavController(),
                )
            }
        )
    }

    override fun onAttach(context: Context) {
        NotesFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<NotesFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
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
            viewModel.navigateToDetail(
                note = null,
                navController = findNavController(),
            )
        }
    }
}