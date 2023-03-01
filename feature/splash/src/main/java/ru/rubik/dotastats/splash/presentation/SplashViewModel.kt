package ru.rubik.dotastats.splash.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.rubik.dotastats.night_mode_api.domain.usecase.NightModeUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase
import ru.rubik.dotastats.splash.di.SplashNavigation
import ru.rubik.dotastats.splash.presentation.state.MainUiState
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val profileIdUseCase: ProfileIdUseCase,
    private val nightModeUseCase: NightModeUseCase,
    private val splashNavigation: SplashNavigation,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(
        MainUiState()
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                AppCompatDelegate.setDefaultNightMode(nightModeUseCase.getNightMode())
            }
            val steamId = profileIdUseCase.getSteamId()

            delay(SPLASH_DELAY_IN_MS)

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

    fun changeStartDestinationByIsSignedIn(activity: Activity) {
        _uiState.value.isSignedIn?.let {
            splashNavigation
                .changeStartDestinationByIsSignedIn(
                    activity = activity,
                    isSignedIn = it,
                )
        }
    }

    companion object {

        private const val SPLASH_DELAY_IN_MS = 1500L
    }
}