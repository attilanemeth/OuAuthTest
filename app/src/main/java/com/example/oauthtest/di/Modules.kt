package com.example.oauthtest.di

import com.example.oauthtest.networking.EndPoints
import com.example.oauthtest.networking.RetrofitBuilder
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
    single { RetrofitBuilder.makeOkHttpClient(androidApplication()) }
    single { RetrofitBuilder.makeRetrofit<EndPoints>(androidApplication(), get()) }
    single { RetrofitBuilder.makeRetroMock(get()) }
    viewModel { LoginViewModel() }
}

val repositoryModule = module {
    factory<PreferencesRepository> { PreferencesRepositoryImpl(androidContext()) }
}