package ru.rubik.dotastats.details.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.details.presentation.EditNoteFeatureComponentDependenciesProvider
import ru.rubik.dotastats.details.presentation.EditNoteFeatureComponentViewModel
import ru.rubik.dotastats.details.presentation.EditNoteViewModel
import ru.rubik.dotastats.details.presentation.EditNoteViewModelAssistedFactory
import ru.rubik.dotastats.details.presentation.state.EditNoteContentState
import ru.rubik.dotastats.details.presentation.state.EditNoteUiState
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.notes.details.R
import ru.rubik.dotastats.notes.details.databinding.FragmentEditNoteBinding
import ru.rubik.dotastats.presentation.ext.parcelable
import javax.inject.Inject

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private val binding: FragmentEditNoteBinding by viewBinding(FragmentEditNoteBinding::bind)

    @Inject
    lateinit var viewModelFactory: EditNoteViewModelAssistedFactory

    private val viewModel: EditNoteViewModel by viewModels {
        viewModelFactory.create(requireArguments().parcelable("note"))
    }

    override fun onAttach(context: Context) {
        EditNoteFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<EditNoteFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
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
        if (uiState.editNoteContentState is EditNoteContentState.Restore) {
            binding.titleEditText.setText(uiState.editNoteContentState.title)
            binding.descriptionEditText.setText(uiState.editNoteContentState.description)
        }
    }
}