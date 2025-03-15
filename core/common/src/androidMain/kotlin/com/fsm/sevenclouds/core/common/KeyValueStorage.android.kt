package com.fsm.sevenclouds.core.common

import android.content.Context
import android.content.SharedPreferences

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KeyValueStorage(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    actual fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    actual fun get(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    actual fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    actual fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    actual fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}
