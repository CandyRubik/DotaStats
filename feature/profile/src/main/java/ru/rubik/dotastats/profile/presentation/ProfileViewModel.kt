package ru.rubik.dotastats.profile.presentation

import android.app.Activity
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.presentation.vm.ProgressBaseViewModel
import ru.rubik.dotastats.profile.di.ProfileNavigation
import ru.rubik.dotastats.profile.domain.repository.ProfileStatsRepository
import ru.rubik.dotastats.profile.domain.usecases.MatchesUseCase
import ru.rubik.dotastats.profile.presentation.state.ContentState
import ru.rubik.dotastats.profile.presentation.state.ProfileUiState
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileIdUseCase: ProfileIdUseCase,
    private val profileUseCase: ProfileUseCase,
    private val matchesUseCase: MatchesUseCase,
    private val profileStatsRepository: ProfileStatsRepository,
    private val profileNavigation: ProfileNavigation,
) : ProgressBaseViewModel() {

    private val _profileUiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            enableLoading()
            val steamId = requireNotNull(profileIdUseCase.getSteamId())
            val profileJob = async {
                profileUseCase.getProfile(steamId)
            }
            val matchesJob = async {
                matchesUseCase.getRecentMatchesInfo(steamId)
            }
            val profileStatJob = async {
                profileStatsRepository.getProfileStats(steamId)
            }
            _profileUiState.update {
                it.copy(
                    profile = profileJob.await(),
                    matchesInfo = matchesJob.await(),
                    profileStats = profileStatJob.await(),
                    contentState = ContentState.Content,
                )
            }
            disableLoading()
        }
    }

    fun onLogoutClocked(activity: Activity) {
        viewModelScope.launch {
            profileIdUseCase.setSteamId(null)
        }
        profileNavigation.navigateToLogin(activity)
    }

    fun navigateToSettings(navController: NavController) {
        profileNavigation.navigateToSettings(navController)
    }
}