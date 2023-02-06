package ru.rubik.dotastats.profile.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.network.response.ApiResponse
import ru.rubik.dotastats.profile.data.entities.ProfileStatResponse

interface ProfileStatApi {

    @GET("players/{steamId}/wl")
    suspend fun getProfileStats(@Path("steamId") id: Long): ApiResponse<ProfileStatResponse>
}