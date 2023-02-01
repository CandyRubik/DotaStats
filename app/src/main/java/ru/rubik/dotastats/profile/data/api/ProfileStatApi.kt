package ru.rubik.dotastats.profile.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.profile.data.entities.ProfileStatResponse
import ru.rubik.dotastats.shared.network.response.ApiResponse

interface ProfileStatApi {

    @GET("players/{steamId}/wl")
    suspend fun getProfileStats(@Path("steamId") id: Long): ApiResponse<ProfileStatResponse>
}