package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.login.domain.repository.LoginRepository
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.login.presentation.state.NavigationState

class LoginViewModel(
    private val loginRepository: LoginRepository,
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

    fun updatePassword(text: String) {
        _loginUiState.update {
            it.copy(
                password = text,
                contentState = NavigationState.Input,
                isLoginButtonAvailable = checkIsLoginButtonAvailable(
                    password = text,
                ),
            )
        }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = loginRepository.login(
                login = _loginUiState.value.login,
                password = _loginUiState.value.password,
            )

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
        password: String? = null
    ): Boolean {
        return password?.isNotBlank() ?: _loginUiState.value.password.isNotBlank()
                && login?.isNotBlank() ?: _loginUiState.value.login.isNotBlank()
    }
}