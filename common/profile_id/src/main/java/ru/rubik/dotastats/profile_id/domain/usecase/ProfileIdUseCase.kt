package ru.rubik.dotastats.profile_id.domain.usecase

import ru.rubik.dotastats.profile_id.domain.repository.ProfileIdRepository

class ProfileIdUseCase(
    private val repository: ProfileIdRepository,
) {
    suspend fun getSteamId(): String? {
        return repository.getProfileId()
    }

    suspend fun setSteamId(steamId: String?) {
        repository.setProfileId(steamId)
    }
}