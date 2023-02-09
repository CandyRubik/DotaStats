package ru.rubik.dotastats.details.data.api

import retrofit2.http.GET
import ru.rubik.dotastats.network.response.ApiResponse

interface HeroesLoreApi {

    @GET("constants/hero_lore")
    suspend fun getHeroesLore(): ApiResponse<HashMap<String, String>>
}