package ru.rubik.dotastats.splash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import ru.rubik.dotastats.R
import ru.rubik.dotastats.shared.data.repository.SteamIdLocalRepository
import ru.rubik.dotastats.splash.presentation.SplashViewModel
import ru.rubik.dotastats.splash.presentation.SplashViewModelFactory
import ru.rubik.dotastats.splash.presentation.state.MainUiState
import ru.rubik.dotastats.splash.usecase.GetSteamIdUseCase

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val splashViewModel: SplashViewModel by viewModels {
        SplashViewModelFactory(
            getSteamIdUseCase = GetSteamIdUseCase(
                repository = SteamIdLocalRepository(
                    sharedPreferences = requireContext().getSharedPreferences(
                        "CREDENTIALS_KEY",
                        AppCompatActivity.MODE_PRIVATE
                    )
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate with SingleLiveEvent from Splash screen
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            splashViewModel.uiState.collect(::obtainUiState)
        }
    }

    private fun obtainUiState(
        uiState: MainUiState,
    ) {
        uiState.isSignedIn?.let {
            val navController = Navigation.findNavController(
                requireActivity(),
                R.id.activity_root__fragment__nav_host
            )

            val mainGraph = navController.navInflater.inflate(R.navigation.app_graph)

            mainGraph.setStartDestination(
                if (uiState.isSignedIn) {
                    R.id.mainFragment
                } else {
                    R.id.auth_graph
                }
            )

            navController.graph = mainGraph
        }
    }
}