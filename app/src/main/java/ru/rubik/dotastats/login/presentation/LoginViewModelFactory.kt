package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.login.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id.domain.repository.ProfileIdRepository

class LoginViewModelFactory(
    private val profileIdRepository: ProfileIdRepository,
    private val profileUseCase: ProfileUseCase,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                profileIdRepository = profileIdRepository,
                profileUseCase = profileUseCase,
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}