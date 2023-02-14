package ru.rubik.dotastats.profile.domain.models

data class MatchInfoRaw(
    val id: Long,
    val heroId: Long,
    val kills: Long,
    val deaths: Long,
    val assists: Long,
    val xpPerMinute: Long,
)