package ru.rubik.dotastats.main.usecase

import ru.rubik.dotastats.shared.steam_id.domain.repository.SteamIdRepository

class GetSteamIdUseCase(
    private val repository: SteamIdRepository,
) {
    suspend fun getSteamId(): String? {
        return repository.getSteamId()
    }
}