package ru.rubik.dotastats.settings.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.di.viewmodel.MultiViewModelFactory
import ru.rubik.dotastats.settings.R
import ru.rubik.dotastats.settings.databinding.FragmentSettingsBinding
import ru.rubik.dotastats.settings.presentation.SettingsFeatureComponentDependenciesProvider
import ru.rubik.dotastats.settings.presentation.SettingsFeatureComponentViewModel
import ru.rubik.dotastats.settings.presentation.SettingsViewModel
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onAttach(context: Context) {
        SettingsFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<SettingsFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.restoreNightMode()

        with(binding) {
            systemThemeButton.setOnClickListener {
                viewModel.onSystemThemeButtonClicked()
            }
            lightThemeButton.setOnClickListener {
                viewModel.onLightThemeButtonClicked()
            }

            darkThemeButton.setOnClickListener {
                viewModel.onDarkThemeButtonClicked()
            }
        }
    }
}