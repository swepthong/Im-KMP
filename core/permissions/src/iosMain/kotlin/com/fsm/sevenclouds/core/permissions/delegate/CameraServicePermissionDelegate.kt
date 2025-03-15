package com.fsm.sevenclouds.core.permissions.delegate

import com.fsm.sevenclouds.core.permissions.model.Permission
import com.fsm.sevenclouds.core.permissions.model.PermissionState
import com.fsm.sevenclouds.core.permissions.util.DeniedAlwaysException
import com.fsm.sevenclouds.core.permissions.util.openAppSettingsPage
import platform.AVFoundation.AVAuthorizationStatus
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class CameraServicePermissionDelegate : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        return when (val status = AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)) {
            AVAuthorizationStatusAuthorized -> PermissionState.GRANTED
            AVAuthorizationStatusNotDetermined -> PermissionState.NOT_DETERMINED
            AVAuthorizationStatusDenied -> PermissionState.DENIED
            else -> error("未知的相机授权状态 $status")
        }
    }

    override suspend fun providePermission() {
        providePermission(AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo))
    }

    private suspend fun providePermission(status: AVAuthorizationStatus) {
        return when (status) {
            AVAuthorizationStatusAuthorized -> return
            AVAuthorizationStatusNotDetermined -> {
                val newStatus = suspendCoroutine<AVAuthorizationStatus> { continuation ->
                    requestCameraAccess { continuation.resume(it) }
                }
                providePermission(newStatus)
            }
            AVAuthorizationStatusDenied -> throw DeniedAlwaysException(Permission.CAMERA)
            else -> error("未知的相机授权状态 $status")
        }
    }

    override fun openSettingPage() {
        openAppSettingsPage()
    }

    private fun requestCameraAccess(callback: (AVAuthorizationStatus) -> Unit) {
        AVCaptureDevice.requestAccessForMediaType(
            AVMediaTypeVideo,
            mainContinuation { granted: Boolean ->
                callback(if (granted) AVAuthorizationStatusAuthorized else AVAuthorizationStatusDenied)
            }
        )
    }
}