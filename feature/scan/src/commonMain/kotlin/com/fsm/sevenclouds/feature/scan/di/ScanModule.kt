package com.fsm.sevenclouds.feature.scan.di

import com.fsm.sevenclouds.core.domain.di.domainModule
import com.fsm.sevenclouds.core.permissions.permissionsModule
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.fsm.sevenclouds.feature.scan.ScanViewModel

val scanModule = module {
    includes(domainModule, permissionsModule)
    viewModelOf(::ScanViewModel)
}
