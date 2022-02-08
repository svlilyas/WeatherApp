package com.mobilion.weatherapp.core.common

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import javax.inject.Inject

private const val PROJECT_SECURE_SHARED_PREF_NAME = "project_secure_shared_pref"
private const val NO_SPACE_CHAR = ""

class ProjectSecureSharedPref @Inject constructor(context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        PROJECT_SECURE_SHARED_PREF_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private val editor: SharedPreferences.Editor = encryptedSharedPreferences.edit()

    fun putString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String): String =
        encryptedSharedPreferences.getString(key, NO_SPACE_CHAR) ?: NO_SPACE_CHAR

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean =
        encryptedSharedPreferences.getBoolean(key, false) ?: false
    /**
     * Removes entry with given [key] from Secure Shared Preferences.
     * After remove request, controls if the key exists or not.
     *
     * @param key Secure shared pref entry key that points to a value.
     * @return A boolean indicating that the entry is no longer resides in secure shared prefs.
     */
    fun removeEntry(key: String): Boolean {
        // Attempting to remove the entry
        editor.remove(key).apply()
        // Controls if the entry exists
        val removeSucceeded = !encryptedSharedPreferences.contains(key)
        return removeSucceeded
    }
}
