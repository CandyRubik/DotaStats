package ru.rubik.dotastats.heroes_api.domain.usecases

import ru.rubik.dotastats.heroes_api.domain.models.Hero
import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository
import javax.inject.Inject

class HeroesUseCase @Inject constructor(
    private val heroRepository: HeroRepository,
) {

    suspend fun getHeroById(id: Long): Hero? {
        return heroRepository.getHeroes().find { it.id == id }
    }

    suspend fun getHeroes(): List<Hero> {
        return heroRepository.getHeroes()
    }
}