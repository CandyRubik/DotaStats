package ru.rubik.dotastats.shared.steamId.domain.repository

interface SteamIdRepository {
    suspend fun getSteamId(): String?
    suspend fun setSteamId(id: String?)
}