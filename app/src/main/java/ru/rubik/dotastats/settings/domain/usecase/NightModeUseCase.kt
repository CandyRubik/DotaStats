package ru.rubik.dotastats.settings.domain.usecase

import ru.rubik.dotastats.settings.domain.repository.NightModeRepository

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