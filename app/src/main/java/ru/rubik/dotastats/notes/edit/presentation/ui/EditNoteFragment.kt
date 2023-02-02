package ru.rubik.dotastats.notes.edit.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentEditNoteBinding
import ru.rubik.dotastats.notes.edit.presentation.EditNoteViewModel
import ru.rubik.dotastats.notes.edit.presentation.EditNoteViewModelFactory
import ru.rubik.dotastats.notes.edit.presentation.state.ContentState
import ru.rubik.dotastats.notes.edit.presentation.state.EditNoteUiState

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private val args by navArgs<EditNoteFragmentArgs>()

    private val binding: FragmentEditNoteBinding by viewBinding(FragmentEditNoteBinding::bind)

    private val viewModel by viewModels<EditNoteViewModel> {
        EditNoteViewModelFactory(args.note)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect(::obtainUiState)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isDeleteButtonEnabled.collect(::obtainDeleteButtonEnabled)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isSaveButtonEnabled.collect(::obtainSaveButtonEnabled)
        }


        binding.saveButton.setOnClickListener {
            viewModel.onSaveButtonClicked()
        }

        binding.removeButton.setOnClickListener {
            viewModel.onRemoveButtonClicked()
        }
    }

    private fun setupViews() {
        binding.titleEditText.doAfterTextChanged {
            viewModel.onTitleChanged(it.toString())
        }
        binding.descriptionEditText.doAfterTextChanged {
            viewModel.onDescriptionChanged(it.toString())
        }
    }

    private fun obtainDeleteButtonEnabled(isEnabled: Boolean) {
        binding.removeButton.isEnabled = isEnabled
    }

    private fun obtainSaveButtonEnabled(isEnabled: Boolean) {
        binding.saveButton.isEnabled = isEnabled
    }

    private fun obtainUiState(uiState: EditNoteUiState) {
        if (uiState.contentState is ContentState.Restore) {
            binding.titleEditText.setText(uiState.contentState.title)
            binding.descriptionEditText.setText(uiState.contentState.description)
        }
    }
}