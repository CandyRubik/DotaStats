package ru.rubik.dotastats.login.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentLoginBinding
import ru.rubik.dotastats.login.presentation.LoginViewModel
import ru.rubik.dotastats.login.presentation.LoginViewModelFactory
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.login.presentation.state.NavigationState
import ru.rubik.dotastats.shared.data.repository.SteamIdLocalRepository
import kotlin.random.Random

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            steamIdRepository = SteamIdLocalRepository(
                sharedPreferences = requireContext().getSharedPreferences(
                    "CREDENTIALS_KEY", AppCompatActivity.MODE_PRIVATE
                )
            )
        )
    }

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect(this@LoginFragment::obtainUiState)
        }
    }

    private fun obtainUiState(
        uiState: LoginUiState,
    ) {
        binding.loginButton.isEnabled = uiState.isLoginButtonAvailable

        when (uiState.contentState) {
            is NavigationState.NavigateToProfile -> {
                val result = findNavController().popBackStack(R.id.auth_graph, true)
                if (result.not()) {
                    // we can't open new destination with this action
                    // --> we opened Auth flow from splash
                    // --> need to open main graph
                    findNavController().navigate(R.id.mainFragment)
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
                val randomIndex = Random.nextInt(emojiList.size);
                val randomEmoji = emojiList[randomIndex]
                binding.title.text = randomEmoji
            }
        }
    }

    private fun setupViews() = with(binding) {
        loginEditText.addTextChangedListener {
            viewModel.updateLogin(it.toString())
        }

        loginButton.setOnClickListener {
            viewModel.login()
        }
    }

    companion object {

        val emojiList = listOf(
            "\uD83D\uDE01", // 😁
            "\uD83D\uDE05", // 😅
            "\uD83E\uDD29", // 🤩
            "\uD83D\uDE36\u200D\uD83C\uDF2B️" // 😶‍🌫️
        )
    }
}
