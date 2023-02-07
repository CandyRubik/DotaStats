package ru.rubik.dotastats.night_mode_api.domain.repository

interface NightModeRepository {

    suspend fun saveMode(mode: Int)

    suspend fun getMode(): Int
}