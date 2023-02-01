package ru.rubik.dotastats.login.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountInfoResponse(
    @SerialName("personaname")
    val username: String,
    @SerialName("avatarfull")
    val avatarUrl: String,
    @SerialName("account_id")
    val id: Long
)