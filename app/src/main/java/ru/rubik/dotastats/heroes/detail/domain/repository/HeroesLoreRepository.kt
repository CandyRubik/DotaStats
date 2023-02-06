package ru.rubik.dotastats.heroes.detail.domain.repository

interface HeroesLoreRepository {
    suspend fun getHeroesLore(): HashMap<String, String>
}