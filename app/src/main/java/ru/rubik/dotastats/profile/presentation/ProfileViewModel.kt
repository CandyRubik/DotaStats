package ru.rubik.dotastats.profile.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.presentation.ProgressBaseViewModel
import ru.rubik.dotastats.profile.presentation.state.ContentState
import ru.rubik.dotastats.profile.presentation.state.ProfileUiState
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator

class ProfileViewModel : ProgressBaseViewModel() {

    private val profileIdUseCase = GlobalServiceLocator.provideProfileIdUseCase()
    private val profileUseCase = GlobalServiceLocator.provideProfileUseCase()
    private val matchesUseCase = GlobalServiceLocator.provideMatchesUseCase()
    private val profileStatsRepository = GlobalServiceLocator.provideProfileStatsRepository()

    private val _profileUiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
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

    fun onLogoutClocked() {
        viewModelScope.launch {
            profileIdUseCase.setSteamId(null)
        }
    }
}