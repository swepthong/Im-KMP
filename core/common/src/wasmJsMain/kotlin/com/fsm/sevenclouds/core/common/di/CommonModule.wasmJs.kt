package com.fsm.sevenclouds.core.common.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module

actual fun provideIoDispatcher() = Dispatchers.Default
actual val keyValueModule: Module
    get() = TODO("Not yet implemented")