package com.example.oauthtest.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("token_type") val tokenType: String?,
    @SerializedName("expires_in") val expireIn: Long?,
    @SerializedName("refresh_token") val refreshToken: String?
)
