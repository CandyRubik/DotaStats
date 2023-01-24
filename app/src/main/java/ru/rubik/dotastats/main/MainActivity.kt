package ru.rubik.dotastats.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.rubik.dotastats.R
import ru.rubik.dotastats.main.presentation.MainViewModel
import ru.rubik.dotastats.main.presentation.MainViewModelFactory
import ru.rubik.dotastats.main.presentation.state.MainUiState
import ru.rubik.dotastats.main.usecase.GetSteamIdUseCase
import ru.rubik.dotastats.shared.data.repository.SteamIdLocalRepository

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            getSteamIdUseCase = GetSteamIdUseCase(
                repository = SteamIdLocalRepository(
                    sharedPreferences = this.getSharedPreferences(
                        CREDENTIALS_KEY,
                        MODE_PRIVATE
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(
            navController
        )

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                obtainUiState(it)
            }
        }
    }

    private fun obtainUiState(uiState: MainUiState) {
//        if (uiState.isSignedIn == true) {
//            findNavController()
//        }
        // TODO navigate to tabs
    }

    companion object {

        const val CREDENTIALS_KEY = "CREDENTIALS_KEY"
    }
}