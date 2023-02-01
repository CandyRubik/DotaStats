package ru.rubik.dotastats.profile.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchInfoRawResponse(
    @SerialName("match_id")
    val id: Long,
    @SerialName("hero_id")
    val heroId: Long,
    @SerialName("kills")
    val kills: Long,
    @SerialName("deaths")
    val deaths: Long,
    @SerialName("assists")
    val assists: Long,
    @SerialName("xp_per_min")
    val xpPerMinute: Long,
)