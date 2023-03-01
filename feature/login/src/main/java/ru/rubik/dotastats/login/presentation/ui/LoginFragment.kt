package ru.rubik.dotastats.login.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.di.viewmodel.MultiViewModelFactory
import ru.rubik.dotastats.login.R
import ru.rubik.dotastats.login.databinding.FragmentLoginBinding
import ru.rubik.dotastats.login.presentation.LoginFeatureComponentDependenciesProvider
import ru.rubik.dotastats.login.presentation.LoginFeatureComponentViewModel
import ru.rubik.dotastats.login.presentation.LoginViewModel
import ru.rubik.dotastats.login.presentation.state.ContentState
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment
import javax.inject.Inject

class LoginFragment : ProgressBaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override fun onAttach(context: Context) {
        LoginFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<LoginFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect(::obtainUiState)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoginButtonAvailable.collect(::obtainLoginButtonEnabled)
        }
    }

    private fun obtainUiState(
        uiState: LoginUiState,
    ) {
        obtainShowErrorToast(uiState.contentState as? ContentState.ShowErrorToast)
        obtainNavigateToProfile(uiState.contentState as? ContentState.NavigateToProfile)
    }

    private fun obtainShowErrorToast(state: ContentState.ShowErrorToast?) {
        state ?: return
        Toast.makeText(
            requireContext(),
            getString(R.string.incorrect_dota_id_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun obtainNavigateToProfile(state: ContentState.NavigateToProfile?) {
        state ?: return
        viewModel.navigateToMain(findNavController())
    }

    private fun obtainLoginButtonEnabled(isEnabled: Boolean) {
        binding.loginButton.isEnabled = isEnabled
    }

    private fun setupViews() = with(binding) {
        loginEditText.addTextChangedListener {
            viewModel.updateLogin(it.toString())
        }

        loginButton.setOnClickListener {
            viewModel.login()
        }
    }
}
