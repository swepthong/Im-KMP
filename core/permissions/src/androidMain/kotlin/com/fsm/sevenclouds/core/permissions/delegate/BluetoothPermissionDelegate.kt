package com.fsm.sevenclouds.core.permissions.delegate

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import com.fsm.sevenclouds.core.permissions.model.Permission
import com.fsm.sevenclouds.core.permissions.model.PermissionState
import com.fsm.sevenclouds.core.permissions.util.PermissionRequestException
import com.fsm.sevenclouds.core.permissions.util.checkPermissions
import com.fsm.sevenclouds.core.permissions.util.openAppSettingsPage
import com.fsm.sevenclouds.core.permissions.util.providePermissions

internal class BluetoothPermissionDelegate(
    private val context: Context,
    private val activity: Lazy<Activity>,
) : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        return checkPermissions(context, activity, bluetoothPermissions)
    }

    override suspend fun providePermission() {
        activity.value.providePermissions(bluetoothPermissions) {
            throw PermissionRequestException(Permission.BLUETOOTH.name)
        }
    }

    override fun openSettingPage() {
        context.openAppSettingsPage(Permission.BLUETOOTH)
    }
}

private val bluetoothPermissions: List<String> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        listOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
        )
    } else listOf(Manifest.permission.BLUETOOTH)
