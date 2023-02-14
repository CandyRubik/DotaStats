package ru.rubik.dotastats.profile_impl.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.profile_impl.data.entities.ProfileResponse
import ru.rubik.dotastats.network.response.ApiResponse

interface ProfileApi {

    @GET("players/{profileId}")
    suspend fun getProfile(@Path("profileId") id: Long): ApiResponse<ProfileResponse>
}