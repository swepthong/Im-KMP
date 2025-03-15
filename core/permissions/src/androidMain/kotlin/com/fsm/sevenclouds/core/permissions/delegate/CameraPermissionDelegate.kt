package com.fsm.sevenclouds.core.permissions.delegate

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import com.fsm.sevenclouds.core.permissions.model.Permission
import com.fsm.sevenclouds.core.permissions.model.PermissionState
import com.fsm.sevenclouds.core.permissions.util.DeniedException
import com.fsm.sevenclouds.core.permissions.util.checkPermissions
import com.fsm.sevenclouds.core.permissions.util.openAppSettingsPage
import com.fsm.sevenclouds.core.permissions.util.providePermissions


internal class CameraPermissionDelegate(
    private val context: Context,
    private val activity: Lazy<Activity>,
) : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        return checkPermissions(context, activity, cameraPermissions)
    }

    override suspend fun providePermission() {
        when (getPermissionState()) {
            PermissionState.GRANTED -> return
            PermissionState.DENIED,
            PermissionState.NOT_DETERMINED -> {
                activity.value.providePermissions(cameraPermissions) {
                    throw Exception(
                        it.localizedMessage ?: "Failed to request camera permission"
                    )
                }
            }
        }
    }

    override fun openSettingPage() {
        context.openAppSettingsPage(Permission.CAMERA)
    }

    private val cameraPermissions: List<String> =
        listOf(Manifest.permission.CAMERA)
}