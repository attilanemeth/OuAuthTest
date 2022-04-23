package com.example.oauthtest.di

import com.example.oauthtest.repositories.PreferencesRepository
import com.example.oauthtest.repositories.impl.PreferencesRepositoryImpl
import com.example.oauthtest.ui.main.LoadingScreenHelper
import com.example.oauthtest.ui.main.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LoadingScreenHelper(androidContext()) }
    viewModel { LoginViewModel() }
}

val repositoryModule = module {
    factory<PreferencesRepository> { PreferencesRepositoryImpl(androidContext()) }
}