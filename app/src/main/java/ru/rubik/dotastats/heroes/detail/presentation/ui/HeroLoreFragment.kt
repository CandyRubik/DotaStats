package ru.rubik.dotastats.heroes.detail.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentHeroLoreBinding
import ru.rubik.dotastats.heroes.detail.presentation.HeroLoreViewModel
import ru.rubik.dotastats.heroes.detail.presentation.HeroLoreViewModelFactory
import ru.rubik.dotastats.heroes.detail.presentation.state.ContentState
import ru.rubik.dotastats.heroes.detail.presentation.state.HeroLoreUiState
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment

class HeroLoreFragment : ProgressBaseFragment(R.layout.fragment_hero_lore) {

    private val navArgs: HeroLoreFragmentArgs by navArgs()

    private val viewBinding: FragmentHeroLoreBinding by viewBinding(FragmentHeroLoreBinding::bind)

    override val viewModel: HeroLoreViewModel by viewModels {
        HeroLoreViewModelFactory(navArgs.heroName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.heroLoreUiState.collect(this@HeroLoreFragment::obtainUiState)
        }
    }

    private fun obtainUiState(state: HeroLoreUiState) {
        if (state.contentState == ContentState.Content) {
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