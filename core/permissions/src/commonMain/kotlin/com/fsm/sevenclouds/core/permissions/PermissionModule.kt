package com.fsm.sevenclouds.core.permissions

import com.fsm.sevenclouds.core.permissions.service.PermissionsService
import com.fsm.sevenclouds.core.permissions.service.PermissionsServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun platformModule(): Module

val permissionsModule: Module = module {
    includes(platformModule())
    single<PermissionsService> { PermissionsServiceImpl() }
}
