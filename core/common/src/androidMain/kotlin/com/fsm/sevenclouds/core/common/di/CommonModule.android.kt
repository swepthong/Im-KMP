package com.fsm.sevenclouds.core.common.di

import com.fsm.sevenclouds.core.common.KeyValueStorage
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


actual val keyValueModule = module {
    single { KeyValueStorage(get()) }  // 使用 Koin 的 `get()` 方法获取 Android 的 Context
}
actual fun provideIoDispatcher() = Dispatchers.IO