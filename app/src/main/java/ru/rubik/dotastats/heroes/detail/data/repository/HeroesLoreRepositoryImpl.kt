package ru.rubik.dotastats.heroes.detail.data.repository

import ru.rubik.dotastats.heroes.detail.data.api.HeroesLoreApi
import ru.rubik.dotastats.heroes.detail.domain.repository.HeroesLoreRepository
import ru.rubik.dotastats.network.response.checkErrors

class HeroesLoreRepositoryImpl(
    private val webApi: HeroesLoreApi
) : HeroesLoreRepository {

    override suspend fun getHeroesLore(): HashMap<String, String> {
        return webApi.getHeroesLore().checkErrors()
    }
}