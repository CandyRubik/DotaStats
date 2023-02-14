package ru.rubik.dotastats.profile.data.repository

import ru.rubik.dotastats.network.response.checkErrors
import ru.rubik.dotastats.profile.data.api.MatchesApi
import ru.rubik.dotastats.profile.data.mappers.MatchesMapper
import ru.rubik.dotastats.profile.domain.models.MatchInfoRaw
import ru.rubik.dotastats.profile.domain.repository.MatchesRepository
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val mapper: MatchesMapper,
    private val webApi: MatchesApi,
) : MatchesRepository {

    override suspend fun getRecentMatches(profileId: String): List<MatchInfoRaw> {
        val response = webApi.getRecentMatches(profileId.toLong()).checkErrors()
        return response.map(mapper::mapToMatchInfoRaw)
    }
}