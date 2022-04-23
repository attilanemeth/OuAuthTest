package com.example.oauthtest.models

sealed class Navigation  {
    object UserFragment:Navigation()
    object LoginFragment:Navigation()
}