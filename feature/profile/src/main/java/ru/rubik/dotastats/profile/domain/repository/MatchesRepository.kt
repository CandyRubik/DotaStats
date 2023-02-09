package ru.rubik.dotastats.profile.domain.repository

import ru.rubik.dotastats.profile.domain.models.MatchInfoRaw

interface MatchesRepository {

    suspend fun getRecentMatches(profileId: String): List<MatchInfoRaw>
}