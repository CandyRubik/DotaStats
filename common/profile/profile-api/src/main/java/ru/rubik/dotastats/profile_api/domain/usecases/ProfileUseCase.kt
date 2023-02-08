package ru.rubik.dotastats.profile_api.domain.usecases

import ru.rubik.dotastats.profile_api.domain.models.Profile
import ru.rubik.dotastats.profile_api.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {

    suspend fun getProfile(steamId: String): Profile? {
        return profileRepository.getProfile(profileId = steamId)
    }
}