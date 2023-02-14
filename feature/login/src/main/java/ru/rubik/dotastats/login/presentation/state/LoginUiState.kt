package ru.rubik.dotastats.login.presentation.state


data class LoginUiState(
    val login: String = "",
    val contentState: ContentState = ContentState.Input,
)

sealed interface ContentState {
    object Input : ContentState
    object NavigateToProfile : ContentState
    object ShowErrorToast : ContentState
}
