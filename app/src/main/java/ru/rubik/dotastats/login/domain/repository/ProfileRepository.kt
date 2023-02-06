package ru.rubik.dotastats.login.domain.repository

import ru.rubik.dotastats.login.domain.models.Profile

interface ProfileRepository {

    suspend fun getProfile(steamId: String): Profile?
}