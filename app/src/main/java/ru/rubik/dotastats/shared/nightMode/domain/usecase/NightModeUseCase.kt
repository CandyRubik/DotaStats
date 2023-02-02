package ru.rubik.dotastats.shared.nightMode.domain.usecase

import ru.rubik.dotastats.shared.nightMode.domain.repository.NightModeRepository

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