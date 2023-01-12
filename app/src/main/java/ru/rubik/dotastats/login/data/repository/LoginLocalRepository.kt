package ru.rubik.dotastats.login.data.repository

import ru.rubik.dotastats.login.domain.entities.User
import ru.rubik.dotastats.login.domain.repository.LoginRepository

class LoginLocalRepository : LoginRepository {

    override suspend fun login(login: String, password: String): User? {
        return if (login == "admin" && password == "admin") {
            User(
                login = login,
                name = "Главный админ \uD83D\uDE01"
            )
        } else {
            null
        }
    }
}