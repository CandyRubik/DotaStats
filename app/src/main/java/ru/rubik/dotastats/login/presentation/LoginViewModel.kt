package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.login.domain.entities.User
import ru.rubik.dotastats.login.domain.repository.LoginRepository
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.login.presentation.state.NavigationState
import ru.rubik.dotastats.shared.steam_id.domain.repository.SteamIdRepository

class LoginViewModel(
    private val steamIdRepository: SteamIdRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun updateLogin(text: String) {
        _loginUiState.update {
            it.copy(
                login = text,
                contentState = NavigationState.Input,
                isLoginButtonAvailable = checkIsLoginButtonAvailable(
                    login = text,
                ),
            )
        }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            steamIdRepository.setSteamId(_loginUiState.value.login)
            val user = User(_loginUiState.value.login)
            if (user == null) {
                _loginUiState.update {
                    it.copy(
                        contentState = NavigationState.ShowErrorToast
                    )
                }
            } else {
                _loginUiState.update {
                    it.copy(
                        contentState = NavigationState.NavigateToProfile(user)
                    )
                }
            }

        }
    }

    private fun checkIsLoginButtonAvailable(
        login: String? = null,
    ): Boolean {
        return login?.isNotBlank() ?: _loginUiState.value.login.isNotBlank()
    }
}