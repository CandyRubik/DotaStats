package ru.rubik.dotastats.heroes.data.mappers

import ru.rubik.dotastats.heroes.data.entities.HeroResponse
import ru.rubik.dotastats.heroes.domain.models.Hero

class HeroesMapper {

    fun mapToHero(source: HeroResponse): Hero {
        return Hero(
            name = source.name,
            turboWinPercent = (source.turboWin * 100) / source.turboTotal
        )
    }
}