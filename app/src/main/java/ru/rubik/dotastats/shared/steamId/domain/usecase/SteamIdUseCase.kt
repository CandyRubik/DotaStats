package ru.rubik.dotastats.shared.steamId.domain.usecase

import ru.rubik.dotastats.shared.steamId.domain.repository.SteamIdRepository

class SteamIdUseCase(
    private val repository: SteamIdRepository,
) {
    suspend fun getSteamId(): String? {
        return repository.getSteamId()
    }

    suspend fun setSteamId(steamId: String?) {
        repository.setSteamId(steamId)
    }
}