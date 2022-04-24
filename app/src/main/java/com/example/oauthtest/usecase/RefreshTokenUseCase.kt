package com.example.oauthtest.usecase

import com.example.oauthtest.networking.Api
import com.example.oauthtest.networking.requests.refreshToken
import com.example.oauthtest.repositories.PreferencesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class RefreshTokenUseCase(
    private val preferencesRepository: PreferencesRepository,
    private val api:Api
) {

    operator fun invoke() = flow {
        val user = preferencesRepository.getString("username")
        //simulating some work
        delay(1500)
        if (user != null) {
            val expiredTime = preferencesRepository.getLong("tokenExpireTime", 0)
            val refreshToken = preferencesRepository.getString("refreshToken") ?: ""
            val currentTime = System.currentTimeMillis()
            val needToRefreshTheKey = expiredTime - currentTime < 0
            if (needToRefreshTheKey) {
                val clintId = preferencesRepository.getString("clientId")
                val result = api.refreshToken(refreshToken, clintId!!)
                when (result) {
                    is Api.Result.Error -> {
                        preferencesRepository.clear()
                        emit(State.RefreshFailed)
                    }
                    is Api.Result.Success -> {
                        val responseData = result.data
                        val tokenExpireTime = System.currentTimeMillis() + (responseData.expireIn!!*1000)
                        preferencesRepository.putString("token", responseData.accessToken!!)
                        preferencesRepository.putString("refreshToken", responseData.refreshToken!!)
                        preferencesRepository.putLong("tokenExpireTime", tokenExpireTime)
                        emit(State.Success)
                    }
                }
            } else {
                emit(State.RefreshNotNeeded)
            }
        } else {
            emit(State.UserNotLoggedIn)
        }
    }


    sealed class State {
        object UserNotLoggedIn: State()
        object Success : State()
        object RefreshNotNeeded : State()
        object RefreshFailed : State()
    }
}