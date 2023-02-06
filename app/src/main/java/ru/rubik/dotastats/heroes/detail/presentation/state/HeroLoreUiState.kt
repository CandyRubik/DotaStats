package ru.rubik.dotastats.heroes.detail.presentation.state

data class HeroLoreUiState(
    val name: String? = null,
    val lore: String? = null,
    val contentState: ContentState = ContentState.Initial,
)

sealed interface ContentState {
    object Initial: ContentState
    object Content: ContentState
}
