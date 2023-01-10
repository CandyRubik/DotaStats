package ru.rubik.dotastats.login.domain.repository

import io.reactivex.rxjava3.core.Single
import ru.rubik.dotastats.login.domain.entities.User

interface LoginRepository {
    fun login(login: String, password: String): Single<User>
}