package com.fsm.sevenclouds.core.common

import platform.Foundation.NSUserDefaults

actual class KeyValueStorage {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    actual fun save(key: String, value: String) {
        userDefaults.setObject(value, key)
    }

    actual fun get(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    actual fun delete(key: String) {
        userDefaults.removeObjectForKey(key)
    }

    actual fun saveInt(key: String, value: Int) {
        userDefaults.setObject(value, key)
    }

    actual fun getInt(key: String): Int {
        return userDefaults.integerForKey(key).toInt()
    }
}
