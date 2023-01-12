package ru.rubik.dotastats.login.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentLoginBinding
import ru.rubik.dotastats.login.data.repository.LoginLocalRepository
import ru.rubik.dotastats.login.presentation.LoginViewModel
import ru.rubik.dotastats.login.presentation.LoginViewModelFactory
import ru.rubik.dotastats.login.presentation.state.NavigationState
import ru.rubik.dotastats.profile.ProfileFragment
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val compositeDisposable = CompositeDisposable()

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            loginRepository = LoginLocalRepository()
        )
    }

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect {
                when (it.contentState) {
                    is NavigationState.NavigateToProfile -> {
                        parentFragmentManager.commit {
                            replace(
                                R.id.container,
                                ProfileFragment.newInstance(it.contentState.user),
                            )
                            addToBackStack(null)
                        }
                    }
                    NavigationState.ShowErrorToast -> {
                        Toast.makeText(
                            requireContext(),
                            "Неверный логин или пароль",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    NavigationState.Input -> {
                    }
                }
            }
        }
    }

    private fun setupViews() {
        val loginObservable = binding.loginEditText.textChanges()
        val passwordObservable = binding.passwordEditText.textChanges()
        val buttonClickObservable = binding.loginButton.clicks()

        loginObservable.debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewModel.updateLogin(it.toString()) }
            .also(compositeDisposable::add)

        passwordObservable.debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewModel.updatePassword(it.toString()) }
            .also(compositeDisposable::add)

        Observable.combineLatest(loginObservable, passwordObservable) { login, password ->
            login.isNotBlank() && password.isNotBlank()
        }.subscribe { enabled ->
            binding.loginButton.isEnabled = enabled
        }.also(compositeDisposable::add)

        buttonClickObservable.subscribe {
            viewModel.login()
        }.also(compositeDisposable::add)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    companion object {

        private const val DEBOUNCE_TIME = 500L
    }
}
