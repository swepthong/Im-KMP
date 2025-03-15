package com.fsm.sevenclouds.feature.message.di

import com.fsm.sevenclouds.core.domain.di.domainModule
import com.fsm.sevenclouds.core.permissions.permissionsModule
import com.fsm.sevenclouds.feature.message.MessageViewModel
import com.fsm.sevenclouds.feature.message.ChatViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val messageModule = module {
    includes(domainModule, permissionsModule)
    viewModelOf(::MessageViewModel)
    viewModelOf(::ChatViewModel)
}
