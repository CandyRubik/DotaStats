package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.login.data.repository.LoginLocalRepository

class LoginViewModelFactory(
    private val loginRepository: LoginLocalRepository,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = loginRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}