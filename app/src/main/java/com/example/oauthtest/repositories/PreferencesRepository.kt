package com.example.oauthtest.repositories

interface PreferencesRepository {
    var prefsName: String
    suspend fun putString(key: String, obj: String)
    suspend fun putBoolean(key: String, obj: Boolean)
    suspend fun putFloat(key: String, obj: Float)
    suspend fun putInt(key: String, obj: Int)
    suspend fun putLong(key: String, obj: Long)
    fun getString(key: String, defVal: String? = null): String?
    fun getBoolean(key: String, defVal: Boolean): Boolean
    fun getFloat(key: String, defVal: Float): Float
    fun getInt(key: String, defVal: Int): Int
    fun getLong(key: String, defVal: Long): Long
    fun clear()


    companion object PrefNames {
        const val ClientInfo = "ClientInfo"

    }
}