package ru.rubik.dotastats.heroes_stats.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentHeroesStatsBinding
import ru.rubik.dotastats.heroes_stats.data.repository.HeroesStatsLocalRepository
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStatsItem
import ru.rubik.dotastats.heroes_stats.presentation.HeroStatsViewModel
import ru.rubik.dotastats.heroes_stats.presentation.HeroStatsViewModelFactory
import ru.rubik.dotastats.heroes_stats.ui.adapter.HeroesStatsAdapter

class HeroesStatsFragment : Fragment(R.layout.fragment_heroes_stats) {

    private val viewModel by viewModels<HeroStatsViewModel> {
        HeroStatsViewModelFactory(
            repository = HeroesStatsLocalRepository()
        )
    }
    private val viewBinding: FragmentHeroesStatsBinding by viewBinding(FragmentHeroesStatsBinding::bind)

    private val adapter by lazy {
        HeroesStatsAdapter(
            diffCallback = object :
                DiffUtil.ItemCallback<HeroStatsItem>() {
                override fun areItemsTheSame(
                    oldItem: HeroStatsItem,
                    newItem: HeroStatsItem
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: HeroStatsItem,
                    newItem: HeroStatsItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.list.collect {
                adapter.itemsList = it
            }
        }
    }
}