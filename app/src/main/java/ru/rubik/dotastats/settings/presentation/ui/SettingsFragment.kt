package ru.rubik.dotastats.settings.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.appComponent
import ru.rubik.dotastats.databinding.FragmentSettingsBinding
import ru.rubik.dotastats.di.MultiViewModelFactory
import ru.rubik.dotastats.settings.di.DaggerSettingsComponent
import ru.rubik.dotastats.settings.presentation.SettingsViewModel
import ru.rubik.dotastats.splash.di.DaggerSplashComponent
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onAttach(context: Context) {
        DaggerSettingsComponent.factory()
            .create(context.appComponent)
            .inject(this)
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