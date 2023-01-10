package ru.rubik.dotastats.settings.domain.repository

interface NightModeRepository {

    suspend fun saveMode(mode: Int)

    suspend fun getMode(): Int
}