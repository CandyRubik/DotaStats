package ru.rubik.dotastats.settings.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.rubik.dotastats.settings.domain.usecase.NightModeUseCase

class SettingsViewModel(
    private val nightModeUseCase: NightModeUseCase,
): ViewModel() {

    fun onSystemThemeButtonClicked() {
        viewModelScope.launch {
            nightModeUseCase.saveNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun onDarkThemeButtonClicked() {
        viewModelScope.launch {
            nightModeUseCase.saveNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    fun onLightThemeButtonClicked() {
        viewModelScope.launch {
            nightModeUseCase.saveNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun restoreNightMode() {
        viewModelScope.launch {
            val mode = nightModeUseCase.getNightMode()
            if(mode != -1) AppCompatDelegate.setDefaultNightMode(mode)
        }
    }
}