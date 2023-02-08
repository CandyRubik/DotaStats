package ru.rubik.dotastats.profile.data.repository

import ru.rubik.dotastats.network.response.checkErrors
import ru.rubik.dotastats.profile.data.api.ProfileStatApi
import ru.rubik.dotastats.profile.data.mappers.ProfileStatsMapper
import ru.rubik.dotastats.profile.domain.models.ProfileStats
import ru.rubik.dotastats.profile.domain.repository.ProfileStatsRepository
import javax.inject.Inject

class ProfileStatsRepositoryImpl @Inject constructor(
    private val mapper: ProfileStatsMapper,
    private val webApi: ProfileStatApi,
): ProfileStatsRepository {

    override suspend fun getProfileStats(profileId: String): ProfileStats {
        val response = webApi.getProfileStats(profileId.toLong()).checkErrors()
        return mapper.mapToProfileStats(response)
    }
}