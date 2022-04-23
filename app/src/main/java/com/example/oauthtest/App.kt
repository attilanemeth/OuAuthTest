package com.example.oauthtest

import android.app.Application
import com.example.oauthtest.di.appModule
import com.example.oauthtest.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repositoryModule))
        }
    }
}