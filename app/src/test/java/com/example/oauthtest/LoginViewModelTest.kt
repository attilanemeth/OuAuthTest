package com.example.oauthtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.test.InstrumentationRegistry.getContext
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.turbine.test
import com.example.oauthtest.di.appModule
import com.example.oauthtest.models.LoginUiModel
import com.example.oauthtest.ui.main.LoginFragment
import com.example.oauthtest.ui.main.viemodels.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest : KoinTest {

    private val testDispatcher = TestCoroutineDispatcher()
    val viewModel: LoginViewModel by inject()

    @Mock
    private lateinit var observer: Observer<LoginUiModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(listOf(appModule))
        }
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        stopKoin()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test the first fucntion`() {
        val result = viewModel.test();
        assertEquals("Hello", result)
    }

    @Test
    //UserName = "hello"
    //Password = "hello
    fun `loggin button enabled if the user and pass field filled  Expexted result = true`() = runBlockingTest {
        val viewModels: LoginViewModel by inject()
        viewModels.username.emit("hello")
        viewModels.password.emit("hello")
        viewModels.content.asLiveData().observeForever(observer)
        verify(observer).onChanged(LoginUiModel(true))
    }

    @Test
    //UserName = "hello"
    //Password = null
    fun `loggin button enabled if the user and pass field filled  Expexted result = False`() = runBlockingTest {
        val viewModels: LoginViewModel by inject()
        viewModels.username.emit("hello")
        viewModels.content.asLiveData().observeForever(observer)
        verify(observer).onChanged(LoginUiModel(false))
    }

    @Test
    fun `show loading screen Expected result = true` () = runBlockingTest {
        viewModel.loginClick()
       val job = launch {
           viewModel.loadingScreen.collect()
       }
        job.cancel()
        assertEquals(true,viewModel.loadingScreen.first())
    }

    @ExperimentalTime
    @Test
    fun `login success navigate to the UserFragment`() = runBlockingTest{
        val viewModels: LoginViewModel by inject()
            launch {

                viewModels.navigation.test {
                    viewModel.loginClick()
                    assertEquals(awaitItem(), LoginFragment)
                    cancelAndConsumeRemainingEvents()
                }
            }
    }
}