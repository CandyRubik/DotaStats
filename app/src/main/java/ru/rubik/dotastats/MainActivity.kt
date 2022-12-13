package ru.rubik.dotastats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import ru.rubik.dotastats.databinding.ActivityMainBinding
import ru.rubik.dotastats.profile.WinLoseStatView

class MainActivity : AppCompatActivity() {
    private val sharedPrefs by lazy {
        getSharedPreferences(THEME_KEY, MODE_PRIVATE)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        restoreDefaultNightMode()

        with(binding) {
            systemThemeButton.setOnClickListener {
                saveAndSetDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            lightThemeButton.setOnClickListener {
                saveAndSetDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            darkThemeButton.setOnClickListener {
                saveAndSetDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            custom.setOnClickListener {
                (it as WinLoseStatView).setPositivePercent(80)
            }

            custom.setOnLongClickListener {
                (it as WinLoseStatView).setPositivePercent(20)
                true
            }
        }
    }

    private fun restoreDefaultNightMode() {
        val currentNightMode = sharedPrefs.getInt(NIGHT_MODE_KEY, -1)

        if(currentNightMode != -1) {
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