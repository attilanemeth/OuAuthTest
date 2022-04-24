package com.example.oauthtest.models

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("grant_type") val grantType: String = "refresh_token",
    @SerializedName("client_id") val clientId: String
)
