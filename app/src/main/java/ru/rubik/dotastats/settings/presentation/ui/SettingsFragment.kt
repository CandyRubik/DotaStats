package ru.rubik.dotastats.settings.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentSettingsBinding
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator
import ru.rubik.dotastats.settings.presentation.SettingsViewModel
import ru.rubik.dotastats.settings.presentation.SettingsViewModelFactory

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel by viewModels<SettingsViewModel> {
        SettingsViewModelFactory(
            nightModeUseCase = GlobalServiceLocator.provideNightModeUseCase()
        )
    }

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)

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

    companion object {

        const val THEME_KEY = "THEME_KEY"
    }
}