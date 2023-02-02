package ru.rubik.dotastats.profile.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentProfileBinding
import ru.rubik.dotastats.profile.domain.models.MatchInfo
import ru.rubik.dotastats.profile.presentation.ProfileViewModel
import ru.rubik.dotastats.profile.presentation.state.ContentState
import ru.rubik.dotastats.profile.presentation.state.ProfileUiState
import ru.rubik.dotastats.profile.presentation.ui.adapter.RecentPlayedMatchAdapter
import ru.rubik.dotastats.shared.heroes.domain.models.Hero
import ru.rubik.dotastats.shared.presentation.ui.ProgressBaseFragment

class ProfileFragment : ProgressBaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override val viewModel: ProfileViewModel by viewModels()

    private val adapter by lazy {
        RecentPlayedMatchAdapter(
            diffCallback = object :
                DiffUtil.ItemCallback<MatchInfo>() {
                override fun areItemsTheSame(
                    oldItem: MatchInfo,
                    newItem: MatchInfo
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MatchInfo,
                    newItem: MatchInfo
                ): Boolean {
                    return oldItem == newItem
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }

        binding.logoutButton.setOnClickListener {
            viewModel.onLogoutClocked()
            requireActivity().findNavController(R.id.activity_root__fragment__nav_host)
                .navigate(R.id.action_mainFragment_to_auth_graph)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.profileUiState.collect(this@ProfileFragment::obtainUiState)
        }
        binding.recycler.adapter = adapter
    }

    private fun obtainUiState(state: ProfileUiState) {
        if (state.contentState == ContentState.Content) {
            with(binding) {
                binding.root.isVisible = true
                state.profile?.let { profile ->
                    username.text = profile.username
                    avatar.load(profile.avatarUrl) {
                        transformations(CircleCropTransformation())
                    }
                }
                state.profileStats?.let { profileStats ->
                    winLoseView.setPositivePercent(profileStats.positivePercent)
                    profileWinrate.text =
                        getString(R.string.profile_winrate_text, profileStats.positivePercent)
                }
                adapter.itemsList = state.matchesInfo
            }
        }
    }
}