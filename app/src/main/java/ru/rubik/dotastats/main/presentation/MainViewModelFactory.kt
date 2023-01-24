package ru.rubik.dotastats.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.main.usecase.GetSteamIdUseCase

class MainViewModelFactory(
    private val getSteamIdUseCase: GetSteamIdUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getSteamIdUseCase = getSteamIdUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}