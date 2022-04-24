package com.example.oauthtest.models

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("username") val username:String,
    @SerializedName("password") val password:String,
    @SerializedName("grant_type") val grantType:String,
    @SerializedName("client_id") val clientId:String
)
