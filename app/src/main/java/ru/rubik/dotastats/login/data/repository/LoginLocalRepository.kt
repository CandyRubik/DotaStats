package ru.rubik.dotastats.login.data.repository

import io.reactivex.rxjava3.core.Single
import ru.rubik.dotastats.login.domain.entities.User
import ru.rubik.dotastats.login.domain.repository.LoginRepository

class LoginLocalRepository : LoginRepository {

    override fun login(login: String, password: String): Single<User> {
        return if (login == "admin" && password == "admin") {
            Single.just(
                User(
                    login = login,
                    name = "Главный админ \uD83D\uDE01"
                )
            )
        } else {
            Single.error(Exception("Can't find user"))
        }
    }
}