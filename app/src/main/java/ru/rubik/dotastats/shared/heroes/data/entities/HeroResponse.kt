package ru.rubik.dotastats.shared.heroes.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("localized_name")
    val name: String,
    @SerialName("img")
    val imageUri: String,
)
