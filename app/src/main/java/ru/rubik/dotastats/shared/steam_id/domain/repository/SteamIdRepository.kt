package ru.rubik.dotastats.shared.steam_id.domain.repository

interface SteamIdRepository {
    suspend fun getSteamId(): String?
    suspend fun setSteamId(id: String?)
}