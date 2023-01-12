package ru.rubik.dotastats.login.presentation.state

import ru.rubik.dotastats.login.domain.entities.User

data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val contentState: NavigationState = NavigationState.Input,
)

sealed interface NavigationState {
    object Input: NavigationState
    data class NavigateToProfile(val user: User): NavigationState
    object ShowErrorToast: NavigationState
}
