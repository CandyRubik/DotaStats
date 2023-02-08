package ru.rubik.dotastats.profile_impl.data.repository

import ru.rubik.dotastats.network.response.checkErrors
import ru.rubik.dotastats.profile_api.domain.models.Profile
import ru.rubik.dotastats.profile_api.domain.repository.ProfileRepository
import ru.rubik.dotastats.profile_impl.data.api.ProfileApi
import ru.rubik.dotastats.profile_impl.data.mappers.ProfileMapper
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val webApi: ProfileApi,
    private val mapper: ProfileMapper,
) : ProfileRepository {

    override suspend fun getProfile(profileId: String): Profile? {
        val response = webApi.getProfile(profileId.toLong()).checkErrors()
        return mapper.mapToProfile(response)
    }
}