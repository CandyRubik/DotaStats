package ru.rubik.dotastats.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.shared.nightMode.domain.usecase.NightModeUseCase

class SettingsViewModelFactory(private val nightModeUseCase: NightModeUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(nightModeUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}