package ru.rubik.dotastats.login.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.rubik.dotastats.login.domain.repository.LoginRepository
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.login.presentation.state.NavigationState

class LoginViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun updateLogin(text: String) {
        _loginUiState.update {
            it.copy(
                login = text,
                contentState = NavigationState.Input
            )
        }
    }

    fun updatePassword(text: String) {
        _loginUiState.update {
            it.copy(
                password = text,
                contentState = NavigationState.Input
            )
        }
    }

    fun login() {
        loginRepository.login(
            login = _loginUiState.value.login,
            password = _loginUiState.value.password,
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ user ->
                _loginUiState.update {
                    it.copy(
                        contentState = NavigationState.NavigateToProfile(user)
                    )
                }
            }, {
                _loginUiState.update {
                    it.copy(
                        contentState = NavigationState.ShowErrorToast
                    )
                }
            }).also(compositeDisposable::add)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}