package ru.rubik.dotastats.shared.nightMode.domain.repository

interface NightModeRepository {

    suspend fun saveMode(mode: Int)

    suspend fun getMode(): Int
}