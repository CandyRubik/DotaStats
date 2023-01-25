package ru.rubik.dotastats.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.shared.extensions.setupWithNavController
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        setupBottomNavigationBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.heroes_graph,
            R.navigation.profile_graph,
            R.navigation.notes_graph,
        )

        binding.fragmentMainBottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.fragment_main__nav_host_container,
            intent = requireActivity().intent
        )
    }
}