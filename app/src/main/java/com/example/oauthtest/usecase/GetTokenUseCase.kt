package com.example.oauthtest.usecase

import com.example.oauthtest.networking.Api
import com.example.oauthtest.networking.requests.getToken
import com.example.oauthtest.repositories.PreferencesRepository
import kotlinx.coroutines.flow.flow
import java.util.*

class GetTokenUseCase(
    private val api: Api,
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke(username: String, password: String) = flow {
        emit(State.Started)
        val clintId = preferencesRepository.getString("clientId") ?: generateClientID()

        val response = api.getToken(username, password, clintId)
        when (response) {
            is Api.Result.Error -> {
                when(val exception = response.exception){
                    is Api.ApiException -> emit(State.ApiError(exception.code))
                    is Api.NetworkException -> emit(State.NetworkError("Check your internet connection"))
                    else -> emit(State.Error(response.exception.message ?: ""))
                }
            }
            is Api.Result.Success -> {
                val responseData = response.data
                val tokenExpireTime = System.currentTimeMillis() + (responseData.expireIn!!*1000)
                preferencesRepository.putString("token", responseData.accessToken!!)
                preferencesRepository.putString("username", username)
                preferencesRepository.putString("refreshToken", responseData.refreshToken!!)
                preferencesRepository.putLong("tokenExpireTime", tokenExpireTime)
                emit(State.Success)
            }
        }
    }

    private suspend fun generateClientID() :String{
        val uuid = UUID.randomUUID().toString()
        preferencesRepository.putString("clientId",uuid)
        return uuid
    }
}

sealed class State {
    object Started : State()
    object Success : State()
    data class Error(val errorMsg: String) : State()
    data class ApiError(val errorCode: Int) : State()
    data class NetworkError(val message: String) : State()
}