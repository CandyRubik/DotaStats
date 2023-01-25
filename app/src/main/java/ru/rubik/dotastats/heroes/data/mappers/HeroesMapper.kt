package ru.rubik.dotastats.heroes.data.mappers

import ru.rubik.dotastats.heroes.data.dto.HeroResponse
import ru.rubik.dotastats.heroes.domain.entities.Hero

class HeroesMapper {

    fun mapToHero(source: HeroResponse): Hero {
        return Hero(
            name = source.name,
            turboWinPercent = (source.turboWin * 100) / source.turboTotal
        )
    }
}