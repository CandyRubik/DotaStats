package ru.rubik.dotastats.heroes_stats.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentHeroesStatsBinding
import ru.rubik.dotastats.heroes_stats.data.repository.HeroesRepositoryLocal
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStat
import ru.rubik.dotastats.heroes_stats.presentation.HeroStatsViewModel
import ru.rubik.dotastats.heroes_stats.presentation.HeroStatsViewModelFactory
import ru.rubik.dotastats.heroes_stats.presentation.ui.adapter.HeroesStatsAdapter

class HeroesStatsFragment : Fragment(R.layout.fragment_heroes_stats) {

    private val viewModel by viewModels<HeroStatsViewModel> {
        HeroStatsViewModelFactory(
            repository = HeroesRepositoryLocal()
        )
    }
    private val viewBinding: FragmentHeroesStatsBinding by viewBinding(FragmentHeroesStatsBinding::bind)

    private val adapter by lazy {
        HeroesStatsAdapter(
            diffCallback = object :
                DiffUtil.ItemCallback<HeroStat>() {
                override fun areItemsTheSame(
                    oldItem: HeroStat,
                    newItem: HeroStat
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: HeroStat,
                    newItem: HeroStat
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