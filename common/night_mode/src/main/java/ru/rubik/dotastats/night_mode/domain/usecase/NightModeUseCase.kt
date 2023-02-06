package ru.rubik.dotastats.night_mode.domain.usecase

import ru.rubik.dotastats.night_mode.domain.repository.NightModeRepository

class NightModeUseCase(
    private val repository: NightModeRepository
) {
    suspend fun saveNightMode(mode: Int) {
        repository.saveMode(mode)
    }

    suspend fun getNightMode(): Int {
        return repository.getMode()
    }
}