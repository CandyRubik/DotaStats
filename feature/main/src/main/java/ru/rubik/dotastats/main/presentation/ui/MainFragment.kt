package ru.rubik.dotastats.main.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.main.R
import ru.rubik.dotastats.main.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_main__nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.fragmentMainBottomNavigation.setupWithNavController(navController)
    }
}