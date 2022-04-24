package com.example.oauthtest.networking

import retrofit2.Response

class Api(
    val endPoints: EndPoints
) {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return Result.Error(Exception())
        }
        return if (!response.isSuccessful) {
            @Suppress("BlockingMethodInNonBlockingContext")
            Result.Error(ApiException(response.code()))
        } else {
            return if (response.body() == null) {
                Result.Error(Exception())
            } else {
                Result.Success(response.body()!!)
            }
        }
    }

    data class ApiException(val code: Int) : Exception()

    sealed class Result<out T : Any> {
        data class Success<out T : Any>(val data: T) : Result<T>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }
}