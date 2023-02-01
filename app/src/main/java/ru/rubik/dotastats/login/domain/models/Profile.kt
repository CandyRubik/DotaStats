package ru.rubik.dotastats.login.domain.models

import java.io.Serializable

data class Profile(
    val username: String,
    val avatarUrl: String,
    val id: String,
) : Serializable