package com.example.oauthtest.repositories.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.oauthtest.repositories.PreferencesRepository

class PreferencesRepositoryImpl(private val context: Context,):PreferencesRepository {
    override var prefsName: String = "TestSharedPrefs"

    private val sharedPrefs: SharedPreferences
        get() = context.getSharedPreferences(prefsName, 0)

    override suspend fun putString(key: String, obj: String) {
        sharedPrefs.edit().apply {
            putString(key, obj)
        }.apply()
    }

    override suspend fun putBoolean(key: String, obj: Boolean) {
        sharedPrefs.edit().apply {
            putBoolean(key, obj)
        }.apply()
    }

    override suspend fun putFloat(key: String, obj: Float) {
        sharedPrefs.edit().apply {
            putFloat(key, obj)
        }.apply()
    }

    override suspend fun putInt(key: String, obj: Int) {
        sharedPrefs.edit().apply {
            putInt(key, obj)
        }.apply()
    }

    override suspend fun putLong(key: String, obj: Long) {
        sharedPrefs.edit().apply {
            putLong(key, obj)
        }.apply()
    }

    override fun getString(key: String, defVal: String?): String? = sharedPrefs.getString(key, defVal)

    override fun getBoolean(key: String, defVal: Boolean): Boolean = sharedPrefs.getBoolean(key, defVal)

    override fun getFloat(key: String, defVal: Float): Float = sharedPrefs.getFloat(key, defVal)

    override fun getInt(key: String, defVal: Int): Int = sharedPrefs.getInt(key, defVal)

    override fun getLong(key: String, defVal: Long): Long = sharedPrefs.getLong(key, defVal)

    override fun clear() {
        sharedPrefs.edit().clear().apply()
    }
}