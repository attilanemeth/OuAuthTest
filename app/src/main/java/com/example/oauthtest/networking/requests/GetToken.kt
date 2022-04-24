package com.example.oauthtest.networking.requests

import com.example.oauthtest.models.AuthRequest
import com.example.oauthtest.networking.Api


suspend fun Api.getToken(username:String,password:String) = apiCall {
    val requst = AuthRequest(username,password,"Password","fsfasfasdfasfasfaasasfa")
    endPoints.getToken(requst)
}

suspend fun Api.refreshToken(username:String,password:String) = apiCall {
    val requst = AuthRequest(username,password,"Password","fsfasfasdfasfasfaasasfa")
    endPoints.refreshToken(requst)
}