package ru.rubik.dotastats.night_mode.domain.repository

interface NightModeRepository {

    suspend fun saveMode(mode: Int)

    suspend fun getMode(): Int
}