package ru.rubik.dotastats.shared.network.response

import java.io.IOException

sealed interface ApiResponse<out T> {
    class Success<T>(val data: T) : ApiResponse<T>

    sealed interface Failure : ApiResponse<Nothing> {
        /**
         * A network failure occurred while communicating to the server
         */
        data class NetworkFailure(val error: IOException) : Failure

        /**
         * A non-2xx HTTP status code was received from the server
         *
         * @property code The HTTP status code.
         * @property message The HTTP status message.
         */
        data class HttpFailure(val code: Int, val message: String) : Failure

        /**
         * An error occurred while converting response body.
         */
        data class ApiFailure(val error: Throwable): Failure

        /**
         * An internal error occurred while attempting to execute a request.
         */
        data class UnknownFailure(val error: Throwable): Failure
    }
}