package com.example.oauthtest.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token") var accessToken: String?,
    @SerializedName("token_type") var tokenType: String?,
    @SerializedName("expires_in") var expireIn: Long?,
    @SerializedName("refresh_token") var refreshToken: String?
)
