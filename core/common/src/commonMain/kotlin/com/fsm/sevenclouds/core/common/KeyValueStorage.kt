package com.fsm.sevenclouds.core.common

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KeyValueStorage {
    fun save(key: String, value: String)
    fun saveInt(key: String, value: Int)
    fun get(key: String): String?
    fun getInt(key: String): Int
    fun delete(key: String)
}