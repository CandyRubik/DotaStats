package ru.rubik.dotastats.profile.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.network.response.ApiResponse
import ru.rubik.dotastats.profile.data.entities.MatchInfoRawResponse

interface MatchesApi {

    @GET("players/{profileId}/recentMatches")
    suspend fun getRecentMatches(@Path("profileId") profileId: Long): ApiResponse<List<MatchInfoRawResponse>>
}