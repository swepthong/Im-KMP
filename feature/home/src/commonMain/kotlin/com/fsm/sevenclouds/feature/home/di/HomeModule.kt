package com.fsm.sevenclouds.feature.home.di

import com.fsm.sevenclouds.core.domain.di.domainModule
import com.fsm.sevenclouds.feature.home.HomeViewModel
import com.fsm.sevenclouds.core.permissions.permissionsModule
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    includes(domainModule, permissionsModule)
    viewModelOf(::HomeViewModel)
}
