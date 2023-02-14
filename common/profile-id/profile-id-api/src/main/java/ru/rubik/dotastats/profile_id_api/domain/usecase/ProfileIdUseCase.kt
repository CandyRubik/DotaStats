package ru.rubik.dotastats.profile_id_api.domain.usecase

import ru.rubik.dotastats.profile_id_api.domain.repository.ProfileIdRepository
import javax.inject.Inject

class ProfileIdUseCase @Inject constructor(
    private val repository: ProfileIdRepository,
) {
    suspend fun getSteamId(): String? {
        return repository.getProfileId()
    }

    suspend fun setSteamId(steamId: String?) {
        repository.setProfileId(steamId)
    }
}