package ru.rubik.dotastats.login.domain.repository

import ru.rubik.dotastats.login.domain.models.Profile

interface ProfileRepository {

    suspend fun getProfile(profileId: String): Profile?
}