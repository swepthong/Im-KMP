package com.fsm.sevenclouds.core.common.di

import com.fsm.sevenclouds.core.common.KeyValueStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module


actual val keyValueModule = module {
    single { KeyValueStorage() }
}
actual fun provideIoDispatcher() = Dispatchers.IO