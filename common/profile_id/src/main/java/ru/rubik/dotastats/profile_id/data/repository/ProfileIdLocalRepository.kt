package ru.rubik.dotastats.profile_id.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import ru.rubik.dotastats.profile_id.domain.repository.ProfileIdRepository

class ProfileIdLocalRepository(
    private val sharedPreferences: SharedPreferences,
): ProfileIdRepository {

    override suspend fun getProfileId(): String? {
        return sharedPreferences.getString(PROFILE_ID_KEY, null)
    }

    override suspend fun setProfileId(id: String?) {
        sharedPreferences.edit(commit = true) {
            putString(PROFILE_ID_KEY, id)
        }
    }

    companion object {
        const val PROFILE_ID_KEY = "STEAM_ID_KEY"
    }
}