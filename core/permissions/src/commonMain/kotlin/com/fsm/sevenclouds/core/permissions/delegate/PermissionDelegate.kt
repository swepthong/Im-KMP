package com.fsm.sevenclouds.core.permissions.delegate

import com.fsm.sevenclouds.core.permissions.model.PermissionState

internal interface PermissionDelegate {
    fun getPermissionState(): PermissionState
    suspend fun providePermission()
    fun openSettingPage()
}
