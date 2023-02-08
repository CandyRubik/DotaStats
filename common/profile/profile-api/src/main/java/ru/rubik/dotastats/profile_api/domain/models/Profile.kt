package ru.rubik.dotastats.profile_api.domain.models

import java.io.Serializable

data class Profile(
    val username: String,
    val avatarUrl: String,
    val id: String,
) : Serializable