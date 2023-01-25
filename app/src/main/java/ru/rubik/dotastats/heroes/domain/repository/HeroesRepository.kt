package ru.rubik.dotastats.heroes.domain.repository

import ru.rubik.dotastats.heroes.domain.entities.Hero

interface HeroesRepository {
    suspend fun getHeroes(): List<Hero>
}