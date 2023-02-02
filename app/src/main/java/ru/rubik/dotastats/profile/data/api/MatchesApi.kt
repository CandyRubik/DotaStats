package ru.rubik.dotastats.profile.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.rubik.dotastats.profile.data.entities.MatchInfoRawResponse
import ru.rubik.dotastats.shared.network.response.ApiResponse

interface MatchesApi {
    @GET("players/{steamId}/recentMatches")
    suspend fun getRecentMatches(@Path("steamId") steamId: Long): ApiResponse<List<MatchInfoRawResponse>>
}