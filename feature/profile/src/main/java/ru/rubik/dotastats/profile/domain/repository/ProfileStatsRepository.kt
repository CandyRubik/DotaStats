package ru.rubik.dotastats.profile.domain.repository

import ru.rubik.dotastats.profile.domain.models.ProfileStats

interface ProfileStatsRepository {
    suspend fun getProfileStats(profileId: String): ProfileStats
}