package com.fsm.sevenclouds.feature.login.di

import com.fsm.sevenclouds.core.domain.di.domainModule
import com.fsm.sevenclouds.feature.login.LoginViewModel
import com.fsm.sevenclouds.core.permissions.permissionsModule
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    includes(domainModule, permissionsModule)
    viewModelOf(::LoginViewModel)
}
