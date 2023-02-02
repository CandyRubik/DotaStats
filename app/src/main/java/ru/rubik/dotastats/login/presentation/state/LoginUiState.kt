package ru.rubik.dotastats.login.presentation.state

import ru.rubik.dotastats.login.domain.models.Profile

data class LoginUiState(
    val login: String = "",
    val contentState: ContentState = ContentState.Input,
)

sealed interface ContentState {
    object Input : ContentState
    object NavigateToProfile : ContentState
    object ShowErrorToast : ContentState
}
