package ru.rubik.dotastats.splash.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import ru.rubik.dotastats.R
import ru.rubik.dotastats.appComponent
import ru.rubik.dotastats.di.MultiViewModelFactory
import ru.rubik.dotastats.splash.di.DaggerSplashComponent
import ru.rubik.dotastats.splash.presentation.SplashViewModel
import ru.rubik.dotastats.splash.presentation.state.MainUiState
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        DaggerSplashComponent.factory()
            .create(context.appComponent)
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate with SingleLiveEvent from Splash screen
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect(::obtainUiState)
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