package ru.rubik.dotastats.profile.data.mappers

import ru.rubik.dotastats.profile.data.entities.ProfileStatResponse
import ru.rubik.dotastats.profile.domain.models.ProfileStats
import javax.inject.Inject

class ProfileStatsMapper @Inject constructor(){
    fun mapToProfileStats(source: ProfileStatResponse): ProfileStats {
        val sum = source.winCount + source.loseCount
        return ProfileStats(
            positivePercent = (source.winCount / sum.toDouble() * 100).toInt()
        )
    }
}