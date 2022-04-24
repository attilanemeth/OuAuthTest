package com.example.oauthtest.ui.main.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oauthtest.models.Navigation
import com.example.oauthtest.networking.Api
import com.example.oauthtest.networking.requests.refreshToken
import com.example.oauthtest.repositories.PreferencesRepository
import com.example.oauthtest.usecase.GetTokenUseCase
import com.example.oauthtest.usecase.RefreshTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AppInitViewModel(
    private val refreshTokenUseCase: RefreshTokenUseCase,
) : ViewModel() {

    val navigation = MutableSharedFlow<Navigation>()

    fun init() {
        refreshTokenUseCase().onEach { state ->
            when(state){
                RefreshTokenUseCase.State.RefreshFailed -> navigation.emit(Navigation.LoginFragment)
                RefreshTokenUseCase.State.RefreshNotNeeded -> navigation.emit(Navigation.UserFragment)
                RefreshTokenUseCase.State.Success -> navigation.emit(Navigation.UserFragment)
                RefreshTokenUseCase.State.UserNotLoggedIn -> navigation.emit(Navigation.LoginFragment)
            }
        }.launchIn(viewModelScope)
    }
}