package ru.rubik.dotastats.heroes.data.api

import retrofit2.http.GET
import ru.rubik.dotastats.network.response.ApiResponse
import ru.rubik.dotastats.heroes.data.entities.HeroResponse

interface HeroesApi {

    @GET("heroStats")
    suspend fun getHeroes(): ApiResponse<List<HeroResponse>>
}