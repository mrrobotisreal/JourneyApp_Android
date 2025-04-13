package io.winapps.journeyapp.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureStorage(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveUserId(userId: String) {
        prefs.edit()
            .putString("userId", userId)
            .apply()
    }

    fun saveUsername(username: String) {
        prefs.edit()
            .putString("username", username)
            .apply()
    }

    fun saveApiKey(apiKey: String) {
        prefs.edit()
            .putString("apiKey", apiKey)
            .apply()
    }

    fun saveToken(token: String) {
        prefs.edit()
            .putString("token", token)
            .apply()
    }

    fun getUserId(): String? {
        val stored = prefs.getString("userId", "")
        return if (stored == "") null else stored
    }

    fun getUsername(): String? {
        val stored = prefs.getString("username", "")
        return if (stored == "") null else stored
    }

    fun getApiKey(): String? {
        return prefs.getString("apiKey", null)
    }

    fun getToken(): String? {
        return prefs.getString("token", null)
    }
}