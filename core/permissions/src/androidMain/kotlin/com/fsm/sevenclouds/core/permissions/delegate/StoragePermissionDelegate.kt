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

internal class StoragePermissionDelegate(
    private val context: Context,
    private val activity: Lazy<Activity>,
) : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        return checkPermissions(context, activity, storagePermissions)
    }

    override suspend fun providePermission() {
        when (getPermissionState()) {
            PermissionState.GRANTED -> return
            PermissionState.DENIED -> throw DeniedException(Permission.WRITE_STORAGE)
            PermissionState.NOT_DETERMINED -> {
                activity.value.providePermissions(storagePermissions) {
                    throw Exception(
                        it.localizedMessage ?: "Failed to request storage permission"
                    )
                }
            }
        }
    }

    override fun openSettingPage() {
        context.openAppSettingsPage(Permission.WRITE_STORAGE)
    }

    private val storagePermissions: List<String> =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else listOf()
}