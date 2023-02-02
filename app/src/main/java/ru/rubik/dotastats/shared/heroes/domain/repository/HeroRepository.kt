package ru.rubik.dotastats.shared.heroes.domain.repository

import ru.rubik.dotastats.shared.heroes.domain.models.Hero

interface HeroRepository {
    suspend fun getHeroes(): List<Hero>
}