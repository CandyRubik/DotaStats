package ru.rubik.dotastats.login.domain.repository

import ru.rubik.dotastats.login.domain.models.Profile
import ru.rubik.dotastats.shared.network.response.ApiResponse

interface ProfileRepository {

    suspend fun getProfile(steamId: String): Profile?
}