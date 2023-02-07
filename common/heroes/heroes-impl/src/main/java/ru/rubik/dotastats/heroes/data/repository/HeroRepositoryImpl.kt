package ru.rubik.dotastats.heroes.data.repository

import ru.rubik.dotastats.network.response.checkErrors
import ru.rubik.dotastats.heroes.data.api.HeroesApi
import ru.rubik.dotastats.heroes.data.mappers.HeroesMapper
import ru.rubik.dotastats.heroes_api.domain.models.Hero
import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository

class HeroRepositoryImpl(
    private val webApi: HeroesApi,
    private val mapper: HeroesMapper,
) : HeroRepository {

    override suspend fun getHeroes(): List<Hero> {
        val response = webApi.getHeroes().checkErrors()
        return response.map(mapper::mapToHero)
    }
}