package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.shared.steam_id.domain.repository.SteamIdRepository

class LoginViewModelFactory(
    private val steamIdRepository: SteamIdRepository,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                steamIdRepository = steamIdRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}