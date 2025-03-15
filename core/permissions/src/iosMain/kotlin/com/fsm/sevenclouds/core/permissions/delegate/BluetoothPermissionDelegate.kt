package com.fsm.sevenclouds.core.permissions.delegate

import com.fsm.sevenclouds.core.permissions.delegate.PermissionDelegate
import com.fsm.sevenclouds.core.permissions.model.PermissionState
import com.fsm.sevenclouds.core.permissions.util.openAppSettingsPage
import platform.CoreBluetooth.CBCentralManager
import platform.CoreBluetooth.CBManagerAuthorizationAllowedAlways
import platform.CoreBluetooth.CBManagerAuthorizationDenied
import platform.CoreBluetooth.CBManagerAuthorizationNotDetermined
import platform.CoreBluetooth.CBManagerAuthorizationRestricted

internal class BluetoothPermissionDelegate : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        return when (CBCentralManager.authorization) {
            CBManagerAuthorizationNotDetermined -> PermissionState.NOT_DETERMINED
            CBManagerAuthorizationAllowedAlways, CBManagerAuthorizationRestricted -> PermissionState.GRANTED
            CBManagerAuthorizationDenied -> PermissionState.DENIED
            else -> PermissionState.NOT_DETERMINED
        }
    }

    override suspend fun providePermission() {
        CBCentralManager().authorization()
    }

    override fun openSettingPage() {
        openAppSettingsPage()
    }
}
