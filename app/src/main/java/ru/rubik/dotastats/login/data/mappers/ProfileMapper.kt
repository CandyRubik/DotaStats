package ru.rubik.dotastats.login.data.mappers

import ru.rubik.dotastats.login.data.entities.ProfileResponse
import ru.rubik.dotastats.login.domain.models.Profile

class ProfileMapper {

    fun mapToProfile(source: ProfileResponse): Profile? {
        return source.account?.let {
            Profile(
                username = it.username,
                avatarUrl = it.avatarUrl,
                id = it.id.toString(),
            )
        }
    }
}