package com.example.oauthtest.ui.main.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oauthtest.repositories.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserViewModel(private val preferencesRepository : PreferencesRepository) : ViewModel() {
    val userName:StateFlow<String>

    init {
        val user = preferencesRepository.getString("username") ?: ""
        userName = MutableStateFlow(user).stateIn(viewModelScope, SharingStarted.Eagerly,user)
    }

}