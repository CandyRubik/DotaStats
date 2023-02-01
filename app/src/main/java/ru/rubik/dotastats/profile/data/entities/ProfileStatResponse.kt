package ru.rubik.dotastats.profile.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileStatResponse(
    @SerialName("win")
    val winCount: Long,
    @SerialName("lose")
    val loseCount: Long,
)