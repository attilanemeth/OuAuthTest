package com.example.oauthtest.ui.main.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oauthtest.models.LoginUiModel
import com.example.oauthtest.models.Navigation
import com.example.oauthtest.repositories.PreferencesRepository
import com.example.oauthtest.usecase.GetTokenUseCase
import com.example.oauthtest.usecase.State
import kotlinx.coroutines.flow.*

class LoginViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private val _loadingScreen = MutableStateFlow(false)
    private val _error = MutableSharedFlow<String>()

    val username = MutableStateFlow<String?>(null)
    val password = MutableStateFlow<String?>(null)
    val navigation = MutableSharedFlow<Navigation>()
    val loadingScreen: Flow<Boolean> get() = _loadingScreen
    val error :MutableSharedFlow<String> get() = _error

    val content = combine(username, password) { user, pw ->
        val isButtonActive = !user.isNullOrEmpty() && !pw.isNullOrEmpty()
        return@combine LoginUiModel(isButtonActive)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, LoginUiModel(false))


    fun loginClick() {
        getTokenUseCase(username.value?:"", password.value?: "").onEach { state ->
            when(state){
                is State.Error -> {
                    _loadingScreen.emit(false)
                    _error.emit("Unexpected Error")
                }
                State.Started -> {
                    _loadingScreen.emit(true)
                }
                State.Success ->{
                    _loadingScreen.emit(false)
                    navigation.emit(Navigation.UserFragment)
                }
                is State.ApiError -> {
                    _loadingScreen.emit(false)
                    when(state.errorCode){
                        401 -> _error.emit("Invalid Username or Password")
                        else -> _error.emit("Unexpected Error")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun test() = "Hello"
}