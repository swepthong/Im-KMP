package com.fsm.sevenclouds.core.common


/*
val someService: PreferenceService = get()
someService.storeData("token", "12345")
 */

class PreferenceService(private val keyValueStorage: KeyValueStorage) {
    fun storeData(key: String, value: String) {
        keyValueStorage.save(key, value)
    }

    fun retrieveData(key: String): String? {
        return keyValueStorage.get(key)
    }

    fun storeIntData(key: String, value: Int) {
        keyValueStorage.saveInt(key, value)
    }

    fun retrieveIntData(key: String): Int {
        return keyValueStorage.getInt(key)
    }

}