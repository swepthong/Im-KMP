package com.fsm.sevenclouds.core.permissions.util

import com.fsm.sevenclouds.core.permissions.delegate.PermissionDelegate
import com.fsm.sevenclouds.core.permissions.model.Permission
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

internal fun KoinComponent.getPermissionDelegate(permission: Permission): PermissionDelegate {
    val permissionDelegate by inject<PermissionDelegate>(named(permission.name))
    return permissionDelegate
}
