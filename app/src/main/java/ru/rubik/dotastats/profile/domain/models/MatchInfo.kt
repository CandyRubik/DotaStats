package ru.rubik.dotastats.profile.domain.models

data class MatchInfo(
    val id: Long,
    val heroUrl: String,
    val kills: Long,
    val deaths: Long,
    val assists: Long,
    val xpPerMinute: Long,
)