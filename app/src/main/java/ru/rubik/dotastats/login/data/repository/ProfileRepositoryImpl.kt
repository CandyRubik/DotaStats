package ru.rubik.dotastats.login.data.repository

import ru.rubik.dotastats.login.data.api.ProfileApi
import ru.rubik.dotastats.login.data.mappers.ProfileMapper
import ru.rubik.dotastats.login.domain.models.Profile
import ru.rubik.dotastats.login.domain.repository.ProfileRepository
import ru.rubik.dotastats.network.response.checkErrors

class ProfileRepositoryImpl(
    private val webApi: ProfileApi,
    private val mapper: ProfileMapper,
): ProfileRepository {

    override suspend fun getProfile(steamId: String): Profile? {
        val response = webApi.getProfile(steamId.toLong()).checkErrors()
        return mapper.mapToProfile(response)
    }
}