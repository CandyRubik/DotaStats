package ru.rubik.dotastats.login.domain.usecases

import ru.rubik.dotastats.login.domain.models.Profile
import ru.rubik.dotastats.login.domain.repository.ProfileRepository

class ProfileUseCase(
    private val profileRepository: ProfileRepository,
) {

    suspend fun getProfile(steamId: String): Profile? {
        return profileRepository.getProfile(profileId = steamId)
    }
}