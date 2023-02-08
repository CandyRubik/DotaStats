package ru.rubik.dotastats.profile_impl.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileResponse(
    @SerialName("profile")
    val account: AccountInfoResponse? = null,
)