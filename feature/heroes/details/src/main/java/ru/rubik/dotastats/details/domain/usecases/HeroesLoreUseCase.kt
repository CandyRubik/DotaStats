package ru.rubik.dotastats.details.domain.usecases

import android.util.Log
import ru.rubik.dotastats.details.domain.repository.HeroesLoreRepository
import javax.inject.Inject

class HeroesLoreUseCase @Inject constructor(
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