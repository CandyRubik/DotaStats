package ru.rubik.dotastats.details.data.repository

import ru.rubik.dotastats.details.data.api.HeroesLoreApi
import ru.rubik.dotastats.details.domain.repository.HeroesLoreRepository
import ru.rubik.dotastats.network.response.checkErrors
import javax.inject.Inject

class HeroesLoreRepositoryImpl @Inject constructor(
    private val webApi: HeroesLoreApi
) : HeroesLoreRepository {

    override suspend fun getHeroesLore(): HashMap<String, String> {
        return webApi.getHeroesLore().checkErrors()
    }
}