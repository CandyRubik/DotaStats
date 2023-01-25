package ru.rubik.dotastats.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator
import ru.rubik.dotastats.shared.steamId.domain.usecase.GetSteamIdUseCase
import ru.rubik.dotastats.splash.presentation.state.MainUiState

class SplashViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(
        MainUiState()
    )
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val getSteamIdUseCase: GetSteamIdUseCase = GlobalServiceLocator.provideGetSteamIdUseCase()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(SPLASH_DELAY_IN_MS)
            val steamId = getSteamIdUseCase.getSteamId()

            if (steamId == null) {
                _uiState.update {
                    it.copy(
                        isSignedIn = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isSignedIn = true
                    )
                }
            }
        }
    }

    companion object {
        private const val SPLASH_DELAY_IN_MS = 1500L
    }
}