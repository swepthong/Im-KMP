package com.fsm.sevenclouds.core.common

actual class KeyValueStorage {
    actual fun save(key: String, value: String) {
    }

    actual fun get(key: String): String? {
        TODO("Not yet implemented")
    }

    actual fun delete(key: String) {
    }

    actual fun saveInt(key: String, value: Int) {
    }

    actual fun getInt(key: String): Int {
        TODO("Not yet implemented")
    }
}