package ru.rubik.dotastats.profile.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.network.response.ApiResponse
import ru.rubik.dotastats.profile.data.entities.MatchInfoRawResponse

interface MatchesApi {

    @GET("players/{steamId}/recentMatches")
    suspend fun getRecentMatches(@Path("steamId") steamId: Long): ApiResponse<List<MatchInfoRawResponse>>
}