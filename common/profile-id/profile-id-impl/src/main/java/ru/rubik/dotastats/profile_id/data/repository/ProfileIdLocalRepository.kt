package ru.rubik.dotastats.profile_id.data.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import ru.rubik.dotastats.profile_id_api.domain.repository.ProfileIdRepository
import javax.inject.Inject

private const val PROFILE_ID_KEY = "STEAM_ID_KEY"
private const val CREDENTIALS_KEY = "CREDENTIALS_KEY"

class ProfileIdLocalRepository @Inject constructor(
    private val context: Context,
) : ProfileIdRepository {

    override suspend fun getProfileId(): String? {
        val sharedPreferences = context.getSharedPreferences(
            CREDENTIALS_KEY, AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.getString(PROFILE_ID_KEY, null)
    }

    override suspend fun setProfileId(id: String?) {
        val sharedPreferences = context.getSharedPreferences(
            CREDENTIALS_KEY, AppCompatActivity.MODE_PRIVATE
        )
        sharedPreferences.edit(commit = true) {
            putString(PROFILE_ID_KEY, id)
        }
    }
}