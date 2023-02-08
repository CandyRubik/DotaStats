package ru.rubik.dotastats.profile.presentation.state

import ru.rubik.dotastats.profile.domain.models.MatchInfo
import ru.rubik.dotastats.profile.domain.models.ProfileStats
import ru.rubik.dotastats.profile_api.domain.models.Profile

data class ProfileUiState(
    val profile: Profile? = null,
    val matchesInfo: List<MatchInfo> = emptyList(),
    val profileStats: ProfileStats? = null,
    val contentState: ContentState = ContentState.Initial,
)

sealed interface ContentState {
    object Initial : ContentState
    object Content : ContentState
}