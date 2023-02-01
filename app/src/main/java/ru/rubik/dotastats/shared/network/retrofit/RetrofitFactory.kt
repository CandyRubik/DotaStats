package ru.rubik.dotastats.shared.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.rubik.dotastats.BuildConfig
import ru.rubik.dotastats.shared.network.response.ApiResponseCallAdapterFactory

object RetrofitFactory {

    val get: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.opendota.com/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
                            else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()
            )
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}