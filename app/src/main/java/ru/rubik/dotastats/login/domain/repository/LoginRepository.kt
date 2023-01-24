package ru.rubik.dotastats.login.domain.repository

import ru.rubik.dotastats.login.domain.entities.User

interface LoginRepository {

    suspend fun login(login: String): User?
}