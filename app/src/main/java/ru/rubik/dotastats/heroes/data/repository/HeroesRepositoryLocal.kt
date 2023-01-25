package ru.rubik.dotastats.heroes.data.repository

import ru.rubik.dotastats.heroes.data.entities.HeroResponse
import ru.rubik.dotastats.heroes.data.mappers.HeroesMapper
import ru.rubik.dotastats.heroes.domain.models.Hero
import ru.rubik.dotastats.heroes.domain.repository.HeroesRepository

class HeroesRepositoryLocal(
    private val webMapper: HeroesMapper,
) : HeroesRepository {

    override suspend fun getHeroes(): List<Hero> {
        return listOf(
            HeroResponse(
                name = "Anti-Mage",
                turboWin = 213339,
                turboTotal = 406818,
            ),
            HeroResponse(
                name = "Axe",
                turboWin = 278749,
                turboTotal = 519059,
            ),
            HeroResponse(
                name = "Bane",
                turboWin = 47966,
                turboTotal = 103555,
            ),
            HeroResponse(
                name = "Bloodseeker",
                turboWin = 309628,
                turboTotal = 103555,
            ),
            HeroResponse(
                name = "Crystal Maiden",
                turboWin = 314697,
                turboTotal = 622079,
            ),
            HeroResponse(
                name = "Drow Ranger",
                turboWin = 467957,
                turboTotal = 855023,
            ),
            HeroResponse(
                name = "Earthshaker",
                turboWin = 236448,
                turboTotal = 481392,
            ),
            HeroResponse(
                name = "Juggernaut",
                turboWin = 548045,
                turboTotal = 1070586,
            ),
        ).map(webMapper::mapToHero)
    }
}