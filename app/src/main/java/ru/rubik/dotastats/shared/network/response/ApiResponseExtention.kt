package ru.rubik.dotastats.shared.network.response

fun <T> ApiResponse<T>.checkErrors(): T = when (this) {
        is ApiResponse.Failure.ApiFailure -> {
            throw this.error
        }
        is ApiResponse.Failure.HttpFailure -> {
            throw Throwable("❌ HttpFailure [${this.code}] ${this.message}")
        }
        is ApiResponse.Failure.NetworkFailure -> {
            throw this.error
        }
        is ApiResponse.Failure.UnknownFailure -> {
            throw this.error
        }
        is ApiResponse.Success -> this.data
    }