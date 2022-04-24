package com.example.oauthtest.networking

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockBehavior
import co.infinum.retromock.meta.MockResponse
import com.example.oauthtest.models.AuthRequest
import com.example.oauthtest.models.AuthResponse
import com.example.oauthtest.models.RefreshTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EndPoints {
    @Mock
    //@MockBehavior(durationMillis = 15000)
   // @MockResponse(code = 401,   body = "ok.json")
    @MockResponse(body = "ok.json")
    @POST("api/v1/token")
    suspend fun getToken(@Body request:AuthRequest) : Response<AuthResponse>


    @Mock
    @POST("api/v1/token")
    //@MockResponse(code = 401,   body = "ok.json")
    @MockResponse(body = "ok.json")
    suspend fun refreshToken(@Body request:RefreshTokenRequest) : Response<AuthResponse>
}