package com.example.oauthtest.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import di.appModule
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class LoginViewModelTest : KoinTest {

    val viewModel : LoginViewModel by inject()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        startKoin {
            modules(listOf(appModule))
        }
    }

    @Test
    fun `test the first fucntion`() {
        val result = viewModel.test();
        assertEquals("Hello", result)
    }
}