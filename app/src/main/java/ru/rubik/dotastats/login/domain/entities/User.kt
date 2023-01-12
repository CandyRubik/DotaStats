package ru.rubik.dotastats.login.domain.entities

import java.io.Serializable

data class User(
    val login: String,
    val name: String,
) : Serializable