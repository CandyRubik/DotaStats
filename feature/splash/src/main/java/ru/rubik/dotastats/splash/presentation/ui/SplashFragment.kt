package ru.rubik.dotastats.splash.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.di.viewmodel.MultiViewModelFactory
import ru.rubik.dotastats.splash.R
import ru.rubik.dotastats.splash.presentation.SplashFeatureComponentDependenciesProvider
import ru.rubik.dotastats.splash.presentation.SplashFeatureComponentViewModel
import ru.rubik.dotastats.splash.presentation.SplashViewModel
import ru.rubik.dotastats.splash.presentation.state.MainUiState
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        SplashFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<SplashFeatureComponentViewModel>().component.inject(this)
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
            if (uiState.isSignedIn) {
                viewModel.changeStartDestinationByIsSignedIn(
                    requireActivity(),
                )
            } else {
                viewModel.changeStartDestinationByIsSignedIn(
                    requireActivity(),
                )
            }
        }
    }
}