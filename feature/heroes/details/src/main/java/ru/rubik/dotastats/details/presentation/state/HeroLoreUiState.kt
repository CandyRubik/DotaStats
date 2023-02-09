package ru.rubik.dotastats.details.presentation.state

data class HeroLoreUiState(
    val name: String? = null,
    val lore: String? = null,
    val heroLoreContentState: HeroLoreContentState = HeroLoreContentState.Initial,
)

sealed interface HeroLoreContentState {
    object Initial : HeroLoreContentState
    object Content : HeroLoreContentState
}
