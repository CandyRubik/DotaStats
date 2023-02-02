package ru.rubik.dotastats.shared.heroes.domain.usecases

import ru.rubik.dotastats.shared.heroes.domain.models.Hero
import ru.rubik.dotastats.shared.heroes.domain.repository.HeroRepository

class HeroesUseCase(
    private val heroRepository: HeroRepository,
) {
    suspend fun getHeroById(id: Long): Hero? {
        return heroRepository.getHeroes().find { it.id == id }
    }

    suspend fun getHeroes(): List<Hero> {
        return heroRepository.getHeroes()
    }
}