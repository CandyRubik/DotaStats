package ru.rubik.dotastats.profile.presentation.state

import ru.rubik.dotastats.login.domain.models.Profile
import ru.rubik.dotastats.profile.domain.models.MatchInfo
import ru.rubik.dotastats.profile.domain.models.ProfileStats

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