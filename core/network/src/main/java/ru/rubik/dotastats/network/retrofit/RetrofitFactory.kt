package ru.rubik.dotastats.network.retrofit

import androidx.viewbinding.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.rubik.dotastats.network.response.ApiResponseCallAdapterFactory

object RetrofitFactory {

    val get: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.opendota.com/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
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