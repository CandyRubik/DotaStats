package ru.rubik.dotastats.login.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.login.data.entities.ProfileResponse
import ru.rubik.dotastats.shared.network.response.ApiResponse

interface ProfileApi {
    @GET("players/{steamId}")
    suspend fun getProfile(@Path("steamId") id: Long): ApiResponse<ProfileResponse>
}