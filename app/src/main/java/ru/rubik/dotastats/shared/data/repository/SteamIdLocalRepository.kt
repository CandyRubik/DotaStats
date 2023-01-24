package ru.rubik.dotastats.shared.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.rubik.dotastats.shared.steam_id.domain.repository.SteamIdRepository

class SteamIdLocalRepository(
    private val sharedPreferences: SharedPreferences,
): SteamIdRepository {

    override suspend fun getSteamId(): String? {
        return sharedPreferences.getString(STEAM_ID_KEY, null)
    }

    override suspend fun setSteamId(id: String?) {
        sharedPreferences.edit(commit = true) {
            putString(STEAM_ID_KEY, id)
        }
    }

    companion object {
        const val STEAM_ID_KEY = "STEAM_ID_KEY"
    }
}