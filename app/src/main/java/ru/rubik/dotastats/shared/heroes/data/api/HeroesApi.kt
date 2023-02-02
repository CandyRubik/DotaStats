package ru.rubik.dotastats.shared.heroes.data.api

import retrofit2.http.GET
import ru.rubik.dotastats.shared.heroes.data.entities.HeroResponse
import ru.rubik.dotastats.shared.network.response.ApiResponse

interface HeroesApi {
    @GET("heroStats")
    suspend fun getHeroes(): ApiResponse<List<HeroResponse>>
}