package ru.rubik.dotastats.details.domain.repository

interface HeroesLoreRepository {
    suspend fun getHeroesLore(): HashMap<String, String>
}