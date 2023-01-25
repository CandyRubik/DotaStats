package ru.rubik.dotastats.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.splash.usecase.GetSteamIdUseCase

class SplashViewModelFactory(
    private val getSteamIdUseCase: GetSteamIdUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(
                getSteamIdUseCase = getSteamIdUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}