package com.fsm.sevenclouds.core.data.di

import com.fsm.sevenclouds.core.common.di.SevencloudsDispatchers
import com.fsm.sevenclouds.core.common.di.commonModule
import com.fsm.sevenclouds.core.data.ChatRepository
import com.fsm.sevenclouds.core.data.ChatRepositoryImpl
import com.fsm.sevenclouds.core.data.UserRepository
import com.fsm.sevenclouds.core.data.UserRepositoryImpl
import com.fsm.sevenclouds.core.network.di.networkModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(commonModule, networkModule)
    single<UserRepository> {
        UserRepositoryImpl(
            sevencloudsDataSource = get(),
            ioSDispatcher = get(named(SevencloudsDispatchers.IO))
        )
    }
    single<ChatRepository> {
        ChatRepositoryImpl(
            ioSDispatcher = get(named(SevencloudsDispatchers.IO))
        )
    }
}