package ru.rubik.dotastats.profile_api.domain.repository

import ru.rubik.dotastats.profile_api.domain.models.Profile

interface ProfileRepository {

    suspend fun getProfile(profileId: String): Profile?
}