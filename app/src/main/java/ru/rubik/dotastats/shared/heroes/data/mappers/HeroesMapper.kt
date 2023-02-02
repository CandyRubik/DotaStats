package ru.rubik.dotastats.shared.heroes.data.mappers

import ru.rubik.dotastats.shared.heroes.data.entities.HeroResponse
import ru.rubik.dotastats.shared.heroes.domain.models.Hero

class HeroesMapper {
    fun mapToHero(source: HeroResponse): Hero {
        return Hero(
            id = source.id,
            name = source.name,
            imageUrl = "https://api.opendota.com${source.imageUri}"
        )
    }
}