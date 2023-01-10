package ru.rubik.dotastats.heroes_stats.domain.repository

import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStat

interface HeroesRepository {
    suspend fun getHeroes(): List<HeroStat>
}