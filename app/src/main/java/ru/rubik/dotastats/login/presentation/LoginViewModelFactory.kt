package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.login.domain.repository.ProfileRepository
import ru.rubik.dotastats.login.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.shared.steamId.domain.repository.SteamIdRepository

class LoginViewModelFactory(
    private val steamIdRepository: SteamIdRepository,
    private val profileUseCase: ProfileUseCase,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                steamIdRepository = steamIdRepository,
                profileUseCase = profileUseCase,
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}