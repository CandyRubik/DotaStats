package ru.rubik.dotastats.profile_impl.data.mappers

import ru.rubik.dotastats.profile_impl.data.entities.ProfileResponse
import ru.rubik.dotastats.profile_api.domain.models.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

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