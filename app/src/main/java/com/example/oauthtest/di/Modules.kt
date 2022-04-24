package com.example.oauthtest.di

import com.example.oauthtest.networking.Api
import com.example.oauthtest.networking.EndPoints
import com.example.oauthtest.networking.RetrofitBuilder
import com.example.oauthtest.repositories.PreferencesRepository
import com.example.oauthtest.repositories.impl.PreferencesRepositoryImpl
import com.example.oauthtest.ui.main.*
import com.example.oauthtest.usecase.GetTokenUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LoadingScreenHelper(androidContext()) }
    single { RetrofitBuilder.makeOkHttpClient(androidApplication()) }
    single { RetrofitBuilder.makeRetrofit(androidApplication(), get()) }
    single { RetrofitBuilder.buildApiServiceWithMock(get()) }
    single { RetrofitBuilder.makeRetroMock(get()) }
    single { Api(get()) }

    factory { GetTokenUseCase(get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { UserViewModel() }
    viewModel { AppInitViewModel(get(),get(),get()) }
}

val repositoryModule = module {
    factory<PreferencesRepository> { PreferencesRepositoryImpl(androidContext()) }
}