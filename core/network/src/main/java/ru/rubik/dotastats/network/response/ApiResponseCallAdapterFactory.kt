package ru.rubik.dotastats.network.response

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory: CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(getRawType(returnType) != Call::class.java) {
            return null
        }
        check(returnType is ParameterizedType) {
            "Call return type must be parameterized as Call<Foo> or Call<out Foo>"
        }
        val innerType = getParameterUpperBound(0, returnType)
        if(getRawType(innerType) != ApiResponse::class.java) {
            return null
        }
        check(innerType is ParameterizedType) {
            "Result must be parameterized as ApiResponse<Foo> or ApiResponse<out Foo>"
        }
        val responseType = getParameterUpperBound(0, innerType)
        return ApiResponseCallAdapter(responseType)
    }
}