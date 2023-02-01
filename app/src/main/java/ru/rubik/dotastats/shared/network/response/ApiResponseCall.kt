package ru.rubik.dotastats.shared.network.response

import android.util.Log
import kotlinx.serialization.SerializationException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ApiResponseCall(private val call: Call<ApiResponse<*>>) : Call<ApiResponse<*>> by call {

    override fun enqueue(callback: Callback<ApiResponse<*>>) {
        call.enqueue(object : Callback<ApiResponse<*>> {
            override fun onResponse(
                call: Call<ApiResponse<*>>,
                response: Response<ApiResponse<*>>
            ) {
                if (response.isSuccessful) {
                    Log.i(null, "✅ Success ${call.request().url}")
                    callback.onResponse(
                        call,
                        Response.success(ApiResponse.Success(checkNotNull(response.body())))
                    )
                } else {
                    Log.w(null, "❌ HttpFailure [${response.code()}] ${call.request().url}")
                    callback.onResponse(
                        call,
                        Response.success(
                            ApiResponse.Failure.HttpFailure(
                                code = response.code(),
                                message = response.message(),
                            )
                        )
                    )
                }
            }

            override fun onFailure(call: Call<ApiResponse<*>>, error: Throwable) {
                when (error) {
                    is IOException -> {
                        Log.e(null, "❌ NetworkFailure ${call.request().url}\n$error")
                        callback.onResponse(
                            call,
                            Response.success(ApiResponse.Failure.NetworkFailure(error))
                        )
                    }
                    is SerializationException -> {
                        Log.e(null, "⛔ ApiFailure ${call.request().url}\n$error")
                        callback.onResponse(call, Response.success(ApiResponse.Failure.ApiFailure(error)))
                    }
                    else -> {
                        Log.e(null, "⛔ UnknownFailure ${call.request().url}\n$error")
                        callback.onResponse(
                            call,
                            Response.success(ApiResponse.Failure.UnknownFailure(error))
                        )
                    }
                }
            }
        })
    }
}