package ru.rubik.dotastats.main.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.di.dependency.findFeatureExternalDependencies
import ru.rubik.dotastats.main.R
import ru.rubik.dotastats.main.databinding.FragmentMainBinding
import ru.rubik.dotastats.main.di.MainNavigation
import ru.rubik.dotastats.main.presentation.MainFeatureComponentDependenciesProvider
import ru.rubik.dotastats.main.presentation.MainFeatureComponentViewModel
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var navigation: MainNavigation
    override fun onAttach(context: Context) {
        MainFeatureComponentDependenciesProvider.featureDependencies =
            findFeatureExternalDependencies()
        ViewModelProvider(this).get<MainFeatureComponentViewModel>().component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_main__nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                navigation.heroesGraphResource,
                navigation.profileGraphResource,
                navigation.notesGraphResource,
            )
        )

        binding.fragmentMainBottomNavigation.setupWithNavController(navController)
    }
}