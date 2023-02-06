package ru.rubik.dotastats.profile_id.domain.repository

interface ProfileIdRepository {
    suspend fun getProfileId(): String?
    suspend fun setProfileId(id: String?)
}