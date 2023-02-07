package ru.rubik.dotastats.night_mode.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.rubik.dotastats.night_mode_api.domain.repository.NightModeRepository

class NightModeLocalRepository(
    private val sharedPreferences: SharedPreferences,
) : NightModeRepository {

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