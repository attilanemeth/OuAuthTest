package com.example.oauthtest.models

open class BaseResponse{
    lateinit var returnCode: ReturnCode
}

enum class ReturnCode(val returnCode: Int) {
    Success(200),
    Unauthorized(401),
    Error(500)
}
