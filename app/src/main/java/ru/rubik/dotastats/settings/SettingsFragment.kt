package ru.rubik.dotastats.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.dotastats.R
import ru.rubik.dotastats.databinding.FragmentSettingsBinding
import ru.rubik.dotastats.heroes_stats.ui.HeroesStatsFragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val sharedPrefs by lazy {
        requireActivity().getSharedPreferences(THEME_KEY, AppCompatActivity.MODE_PRIVATE)
    }

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restoreDefaultNightMode()

        with(binding) {
            systemThemeButton.setOnClickListener {
                saveAndSetDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            lightThemeButton.setOnClickListener {
                saveAndSetDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO)
            }

            darkThemeButton.setOnClickListener {
                saveAndSetDefaultNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES)
            }

            custom.setOnClickListener {
                parentFragmentManager.commit {
                    replace<HeroesStatsFragment>(R.id.container)
                }
            }
        }
    }

    private fun restoreDefaultNightMode() {
        val currentNightMode = sharedPrefs.getInt(NIGHT_MODE_KEY, -1)

        if (currentNightMode != -1) {
            AppCompatDelegate.setDefaultNightMode(currentNightMode)
        }
    }

    private fun saveAndSetDefaultNightMode(defaultNightMode: Int) {
        sharedPrefs.edit(commit = true) {
            putInt(NIGHT_MODE_KEY, defaultNightMode)
        }
        AppCompatDelegate.setDefaultNightMode(defaultNightMode)
    }

    companion object {

        const val THEME_KEY = "THEME_KEY"
        const val NIGHT_MODE_KEY = "NIGHT_MODE_KEY"
    }
}