package ru.rubik.dotastats.heroes_stats.data.mappers

import ru.rubik.dotastats.heroes_stats.data.dto.HeroStats
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStat

fun HeroStats.mapToDomain(): HeroStat {
    return HeroStat(
        name = name,
        turboWinPercent = (turboWin * 100) / turboTotal
    )
}