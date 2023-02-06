package ru.rubik.dotastats.heroes.detail.domain.usecases

import android.util.Log
import ru.rubik.dotastats.heroes.detail.domain.repository.HeroesLoreRepository

class HeroesLoreUseCase(
    private val repository: HeroesLoreRepository,
) {
    suspend fun getHeroLore(name: String): String {
        val lore = repository.getHeroesLore()
        val computedName = name.lowercase().replace(" ", "_").replace("-", "")
        Log.i(null, "SIZE ${lore.size}")
        Log.i(null, "NAME $computedName")
        return lore.getOrElse(computedName) {
            throw IllegalStateException("${this::class.java} Can't get lore by name")
        }
    }

}