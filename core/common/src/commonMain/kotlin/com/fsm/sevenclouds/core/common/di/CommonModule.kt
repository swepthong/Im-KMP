package com.fsm.sevenclouds.core.common.di

import com.fsm.sevenclouds.core.common.PreferenceService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    includes(keyValueModule)
    single(named(SevencloudsDispatchers.IO)) { provideIoDispatcher() }
    single(named(SevencloudsDispatchers.Default)) { Dispatchers.Default }
    single<CoroutineScope> {
        provideApplicationScope(get(named(SevencloudsDispatchers.Default)))
    }
    single { PreferenceService(get()) }
}

expect val keyValueModule: Module

expect fun provideIoDispatcher(): CoroutineDispatcher
fun provideApplicationScope(dispatcher: CoroutineDispatcher): CoroutineScope =
    CoroutineScope(SupervisorJob() + dispatcher)


enum class SevencloudsDispatchers {
    IO, Default
}