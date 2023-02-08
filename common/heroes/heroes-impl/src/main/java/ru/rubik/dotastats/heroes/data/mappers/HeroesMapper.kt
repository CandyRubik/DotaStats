package ru.rubik.dotastats.heroes.data.mappers

import ru.rubik.dotastats.heroes.data.entities.HeroResponse
import ru.rubik.dotastats.heroes_api.domain.models.Hero
import javax.inject.Inject

class HeroesMapper @Inject constructor() {
    fun mapToHero(source: HeroResponse): Hero {
        return Hero(
            id = source.id,
            name = source.name,
            imageUrl = "https://api.opendota.com${source.imageUri}"
        )
    }
}