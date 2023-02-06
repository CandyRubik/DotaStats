package ru.rubik.dotastats.heroes.domain.repository

import ru.rubik.dotastats.heroes.domain.models.Hero

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
}