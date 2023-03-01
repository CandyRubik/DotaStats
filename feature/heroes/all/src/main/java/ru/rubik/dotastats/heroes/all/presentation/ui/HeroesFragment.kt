package ru.rubik.dotastats.heroes.all.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.di.viewmodel.MultiViewModelFactory
import ru.rubik.dotastats.heroes.all.R
import ru.rubik.dotastats.heroes.all.databinding.FragmentHeroesBinding
import ru.rubik.dotastats.heroes.all.di.HeroesNavigation
import ru.rubik.dotastats.heroes.all.presentation.HeroesFeatureComponentDependenciesProvider
import ru.rubik.dotastats.heroes.all.presentation.HeroesFeatureComponentViewModel
import ru.rubik.dotastats.heroes.all.presentation.HeroesViewModel
import ru.rubik.dotastats.heroes.all.presentation.ui.adapter.HeroItemAnimator
import ru.rubik.dotastats.heroes.all.presentation.ui.adapter.HeroesAdapter
import ru.rubik.dotastats.heroes_api.domain.models.Hero
import ru.rubik.dotastats.presentation.ui.ProgressBaseFragment
import javax.inject.Inject

class HeroesFragment : ProgressBaseFragment(R.layout.fragment_heroes) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: HeroesViewModel by viewModels { viewModelFactory }

    private val viewBinding: FragmentHeroesBinding by viewBinding(FragmentHeroesBinding::bind)

    @Inject
    lateinit var navigation: HeroesNavigation

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
                viewModel.navigateToDetail(findNavController(), hero.name)
            }
        )
    }

    override fun onAttach(context: Context) {
        HeroesFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<HeroesFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recycler.itemAnimator = HeroItemAnimator()
        viewBinding.recycler.adapter = adapter
        viewBinding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchData()
            viewBinding.swipeToRefresh.isRefreshing = false
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.list.collect {
                adapter.itemsList = it
            }
        }
    }
}