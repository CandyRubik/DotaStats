package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.login.presentation.state.ContentState
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.presentation.vm.ProgressBaseViewModel
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val profileIdUseCase: ProfileIdUseCase,
    private val profileUseCase: ProfileUseCase,
) : ProgressBaseViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    val isLoginButtonAvailable = _loginUiState.map {
        _loginUiState.value.login.isNotBlank()
    }

    fun updateLogin(text: String) {
        _loginUiState.update {
            it.copy(
                login = text,
                contentState = ContentState.Input,
            )
        }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            enableLoading()
            val profile = profileUseCase.getProfile(_loginUiState.value.login)
            if (profile == null) {
                _loginUiState.update {
                    it.copy(
                        contentState = ContentState.ShowErrorToast
                    )
                }
            } else {
                profileIdUseCase.setSteamId(_loginUiState.value.login)
                _loginUiState.update {
                    it.copy(
                        contentState = ContentState.NavigateToProfile
                    )
                }
            }
        }
        disableLoading()
    }
}