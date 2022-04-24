package com.example.oauthtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oauthtest.models.Navigation
import com.example.oauthtest.networking.Api
import com.example.oauthtest.networking.requests.refreshToken
import com.example.oauthtest.repositories.PreferencesRepository
import com.example.oauthtest.usecase.GetTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AppInitViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val api: Api,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val navigation = MutableSharedFlow<Navigation>()

    fun init() {
        viewModelScope.launch {
            val user = preferencesRepository.getString("username")
            delay(1500)
            if (user != null) {
                val expiredTime = preferencesRepository.getLong("tokenExpireTime", 0)
                val currentTime = System.currentTimeMillis()
                val needToRefreshTheKey = expiredTime - currentTime < 0

                if (needToRefreshTheKey) {
                    val result = api.refreshToken("fsfs", "fdsfs")
                    when (result) {
                        is Api.Result.Error -> {
                            preferencesRepository.clear()
                            navigation.emit(Navigation.LoginFragment)
                        }
                        is Api.Result.Success -> {
                            navigation.emit(Navigation.UserFragment)
                        }
                    }
                } else {
                    navigation.emit(Navigation.UserFragment)
                }
            } else {
                navigation.emit(Navigation.LoginFragment)
            }
        }
    }
}