package ru.rubik.dotastats.profile.domain.usecases

import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository
import ru.rubik.dotastats.profile.domain.models.MatchInfo
import ru.rubik.dotastats.profile.domain.repository.MatchesRepository
import javax.inject.Inject

class MatchesUseCase @Inject constructor(
    private val matchesRepository: MatchesRepository,
    private val heroesRepository: HeroRepository,
) {

    suspend fun getRecentMatchesInfo(profileId: String): List<MatchInfo> {
        val matches = matchesRepository.getRecentMatches(profileId)
        val heroes = heroesRepository.getHeroes()
        return matches.map {
            MatchInfo(
                id = it.id,
                heroUrl = try {
                    heroes.first { hero -> hero.id == it.heroId }.imageUrl
                } catch (e: NoSuchElementException) {
                    DEFAULT_HERO_IMAGE_URL
                }, // api returns heroes list less then heroes available
                kills = it.kills,
                deaths = it.deaths,
                assists = it.assists,
                xpPerMinute = it.xpPerMinute,
            )
        }
    }

    companion object {

        const val DEFAULT_HERO_IMAGE_URL =
            "https://icon-library.com/images/steam-question-mark-icon/steam-question-mark-icon-5.jpg"
    }
}