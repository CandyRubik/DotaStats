package ru.rubik.dotastats.heroes_stats.data.repository

import ru.rubik.dotastats.heroes_stats.data.dto.HeroStats
import ru.rubik.dotastats.heroes_stats.data.mappers.mapToDomain
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStatsItem

class HeroesStatsLocalRepository {
    suspend fun getHeroes(): List<HeroStatsItem> {
        return listOf(
            HeroStats(
                name = "Anti-Mage",
                turboWin = 213339,
                turboTotal = 406818,
            ),
            HeroStats(
                name = "Axe",
                turboWin = 278749,
                turboTotal = 519059,
            ),
            HeroStats(
                name = "Bane",
                turboWin = 47966,
                turboTotal = 103555,
            ),
            HeroStats(
                name = "Bloodseeker",
                turboWin = 309628,
                turboTotal = 103555,
            ),
            HeroStats(
                name = "Crystal Maiden",
                turboWin = 314697,
                turboTotal = 622079,
            ),
            HeroStats(
                name = "Drow Ranger",
                turboWin = 467957,
                turboTotal = 855023,
            ),
            HeroStats(
                name = "Earthshaker",
                turboWin = 236448,
                turboTotal = 481392,
            ),
            HeroStats(
                name = "Juggernaut",
                turboWin = 548045,
                turboTotal = 1070586,
            ),
        ).map {
            it.mapToDomain()
        }
    }
}