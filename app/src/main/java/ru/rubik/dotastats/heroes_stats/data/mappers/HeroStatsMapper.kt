package ru.rubik.dotastats.heroes_stats.data.mappers

import ru.rubik.dotastats.heroes_stats.data.dto.HeroStats
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStatsItem

fun HeroStats.mapToDomain(): HeroStatsItem {
    return HeroStatsItem(
        name = name,
        turboWinPercent = (turboWin * 100) / turboTotal
    )
}