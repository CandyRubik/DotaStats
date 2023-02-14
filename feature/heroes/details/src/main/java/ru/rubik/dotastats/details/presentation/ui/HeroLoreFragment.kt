package ru.rubik.dotastats.details.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.details.presentation.HeroLoreViewModel
import ru.rubik.dotastats.details.presentation.HeroesLoreFeatureComponentDependenciesProvider
import ru.rubik.dotastats.details.presentation.HeroesLoreFeatureComponentViewModel
import ru.rubik.dotastats.details.presentation.HeroesLoreViewModelAssistedFactory
import ru.rubik.dotastats.details.presentation.state.HeroLoreContentState
import ru.rubik.dotastats.details.presentation.state.HeroLoreUiState
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.heroes.details.R
import ru.rubik.dotastats.heroes.details.databinding.FragmentHeroLoreBinding
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment
import javax.inject.Inject

class HeroLoreFragment : ProgressBaseFragment(R.layout.fragment_hero_lore) {

    private val viewBinding: FragmentHeroLoreBinding by viewBinding(FragmentHeroLoreBinding::bind)

    @Inject
    lateinit var viewModelFactory: HeroesLoreViewModelAssistedFactory

    override val viewModel: HeroLoreViewModel by viewModels {
        viewModelFactory.create(requireArguments().getString("heroName", null))
    }

    override fun onAttach(context: Context) {
        HeroesLoreFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<HeroesLoreFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.heroLoreUiState.collect(this@HeroLoreFragment::obtainUiState)
        }
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchData()
            viewBinding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun obtainUiState(state: HeroLoreUiState) {
        if (state.heroLoreContentState == HeroLoreContentState.Content) {
            with(viewBinding) {
                subheader.isVisible = true
                state.lore?.let {
                    lore.text = it
                }
                state.name?.let {
                    name.text = it
                }
            }
        }
    }
}