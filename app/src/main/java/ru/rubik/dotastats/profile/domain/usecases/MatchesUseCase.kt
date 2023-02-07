package ru.rubik.dotastats.profile.domain.usecases

import ru.rubik.dotastats.profile.domain.models.MatchInfo
import ru.rubik.dotastats.profile.domain.repository.MatchesRepository
import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository

class MatchesUseCase(
    private val matchesRepository: MatchesRepository,
    private val heroesRepository: HeroRepository,
) {
    suspend fun getRecentMatchesInfo(profileId: String): List<MatchInfo> {
        val matches = matchesRepository.getRecentMatches(profileId)
        val heroes = heroesRepository.getHeroes().sortedBy {
            it.id
        }.toMutableList()
        return matches.map {
            MatchInfo(
                id = it.id,
                heroUrl = heroes[(it.heroId - 1).toInt()].imageUrl,
                kills = it.kills,
                deaths = it.deaths,
                assists = it.assists,
                xpPerMinute = it.xpPerMinute,
            )
        }
    }
}