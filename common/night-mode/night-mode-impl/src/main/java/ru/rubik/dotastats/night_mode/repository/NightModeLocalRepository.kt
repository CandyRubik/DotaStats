package ru.rubik.dotastats.night_mode.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import ru.rubik.dotastats.night_mode_api.domain.repository.NightModeRepository
import javax.inject.Inject

private const val NIGHT_MODE_KEY = "NIGHT_MODE_KEY"
private const val THEME_KEY = "THEME_KEY"

class NightModeLocalRepository @Inject constructor(
    private val context: Context,
) : NightModeRepository {

    override suspend fun saveMode(mode: Int) {
        val sharedPreferences = context.getSharedPreferences(
            THEME_KEY,
            AppCompatActivity.MODE_PRIVATE
        )
        sharedPreferences.edit(commit = true) {
            putInt(NIGHT_MODE_KEY, mode)
        }
    }

    override suspend fun getMode(): Int {
        val sharedPreferences = context.getSharedPreferences(
            THEME_KEY,
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.getInt(NIGHT_MODE_KEY, -1)
    }
}