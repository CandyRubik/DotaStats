package ru.rubik.dotastats.settings.data.repository

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import ru.rubik.dotastats.settings.domain.repository.NightModeRepository
import ru.rubik.dotastats.settings.presentation.ui.SettingsFragment

class NightModeLocalRepository(
    private val sharedPreferences: SharedPreferences,
): NightModeRepository {

    override suspend fun saveMode(mode: Int) {
        sharedPreferences.edit(commit = true) {
            putInt(NIGHT_MODE_KEY, mode)
        }
    }

    override suspend fun getMode(): Int {
        return sharedPreferences.getInt(NIGHT_MODE_KEY, -1)
    }

    companion object {
        const val NIGHT_MODE_KEY = "NIGHT_MODE_KEY"
    }
}