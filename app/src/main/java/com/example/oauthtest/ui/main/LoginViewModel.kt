package com.example.oauthtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oauthtest.models.LoginUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _loadingScreen = MutableStateFlow(false)

    val username = MutableStateFlow<String?>(null)
    val password = MutableStateFlow<String?>(null)
    val loadingScreen: Flow<Boolean> get() = _loadingScreen

    val content = combine(username, password) { user, pw ->
        val isButtonActive = !user.isNullOrEmpty() && !pw.isNullOrEmpty()
        return@combine LoginUiModel(isButtonActive)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, LoginUiModel(false))

    fun loginClick() {
        viewModelScope.launch {
            _loadingScreen.emit(t
        }
    }

    fun test() = "Hello"
}