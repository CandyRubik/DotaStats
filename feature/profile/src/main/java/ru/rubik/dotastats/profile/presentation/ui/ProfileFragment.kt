package ru.rubik.dotastats.profile.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.di.viewmodel.MultiViewModelFactory
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment
import ru.rubik.dotastats.profile.R
import ru.rubik.dotastats.profile.databinding.FragmentProfileBinding
import ru.rubik.dotastats.profile.di.ProfileNavigation
import ru.rubik.dotastats.profile.domain.models.MatchInfo
import ru.rubik.dotastats.profile.presentation.ProfileFeatureComponentDependenciesProvider
import ru.rubik.dotastats.profile.presentation.ProfileFeatureComponentViewModel
import ru.rubik.dotastats.profile.presentation.ProfileViewModel
import ru.rubik.dotastats.profile.presentation.state.ContentState
import ru.rubik.dotastats.profile.presentation.state.ProfileUiState
import ru.rubik.dotastats.profile.presentation.ui.adapter.RecentPlayedMatchAdapter
import javax.inject.Inject

class ProfileFragment : ProgressBaseFragment(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    @Inject
    lateinit var navigation: ProfileNavigation

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

    override fun onAttach(context: Context) {
        ProfileFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<ProfileFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        setUpObservers()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        binding.recycler.setHasFixedSize(false)
        binding.recycler.isNestedScrollingEnabled = false
        binding.recycler.adapter = adapter
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.profileUiState.collect(this@ProfileFragment::obtainUiState)
        }
    }

    private fun setUpListeners() {
        binding.settingsButton.setOnClickListener {
            findNavController().navigate(navigation.actionProfileFragmentToSettingsFragment)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchData()
            binding.swipeToRefresh.isRefreshing = false
        }

        binding.logoutButton.setOnClickListener {
            viewModel.onLogoutClocked()
            requireActivity().findNavController(navigation.activityNavHost)
                .navigate(navigation.actionMainFragmentToAuthGraph)
        }
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