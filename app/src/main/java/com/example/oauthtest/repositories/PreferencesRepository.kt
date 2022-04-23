package com.example.oauthtest.repositories

interface PreferencesRepository {
    var prefsName: String
    suspend fun putString(key: String, obj: String)
    suspend fun putBoolean(key: String, obj: Boolean)
    suspend fun putFloat(key: String, obj: Float)
    suspend fun putInt(key: String, obj: Int)
    suspend fun putLong(key: String, obj: Long)


    companion object PrefNames {
        const val ClientInfo = "ClientInfo"

    }
}