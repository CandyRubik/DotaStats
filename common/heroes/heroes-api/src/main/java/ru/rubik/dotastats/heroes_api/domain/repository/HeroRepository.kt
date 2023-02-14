package ru.rubik.dotastats.heroes_api.domain.repository

import ru.rubik.dotastats.heroes_api.domain.models.Hero

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
}