package com.example.oauthtest.networking.requests

import com.example.oauthtest.models.AuthRequest
import com.example.oauthtest.models.RefreshTokenRequest
import com.example.oauthtest.networking.Api


suspend fun Api.getToken(username:String,password:String, cliendId: String) = apiCall {
    val requst = AuthRequest(username,password,"Password",cliendId)
    endPoints.getToken(requst)
}

suspend fun Api.refreshToken(refreshToken:String,cliendId:String) = apiCall {
    val requst = RefreshTokenRequest(refreshToken, clientId =cliendId)
    endPoints.refreshToken(requst)
}