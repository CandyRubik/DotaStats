package ru.rubik.dotastats.heroes.all.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentHeroesStatsBinding
import ru.rubik.dotastats.heroes.all.presentation.HeroesViewModel
import ru.rubik.dotastats.heroes.all.presentation.HeroesViewModelFactory
import ru.rubik.dotastats.heroes.all.presentation.ui.adapter.HeroesAdapter
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator
import ru.rubik.dotastats.heroes_api.domain.models.Hero

class HeroesFragment : ProgressBaseFragment(R.layout.fragment_heroes_stats) {

    override val viewModel by viewModels<HeroesViewModel> {
        HeroesViewModelFactory(
            repository = GlobalServiceLocator.provideHeroesRepository()
        )
    }

    private val viewBinding: FragmentHeroesStatsBinding by viewBinding(FragmentHeroesStatsBinding::bind)

    private val adapter by lazy {
        HeroesAdapter(
            diffCallback = object :
                DiffUtil.ItemCallback<Hero>() {
                override fun areItemsTheSame(
                    oldItem: Hero,
                    newItem: Hero
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Hero,
                    newItem: Hero
                ): Boolean {
                    return oldItem == newItem
                }
            },
            onItemClickListener = { hero ->
                val action =
                    HeroesFragmentDirections.actionHeroesStatsFragmentToHeroLoreFragment(hero.name)
                findNavController().navigate(action)
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