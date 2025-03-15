package com.fsm.sevenclouds.core.permissions.delegate

import com.fsm.sevenclouds.core.permissions.delegate.PermissionDelegate
import com.fsm.sevenclouds.core.permissions.model.PermissionState
import com.fsm.sevenclouds.core.permissions.util.openNSUrl
import platform.CoreLocation.CLLocationManager

internal class LocationServicePermissionDelegate : PermissionDelegate {
    private val locationManager = CLLocationManager()

    override fun getPermissionState(): PermissionState {
        return if (locationManager.locationServicesEnabled())
            PermissionState.GRANTED else PermissionState.DENIED
    }

    override suspend fun providePermission() {
        openSettingPage()
    }

    override fun openSettingPage() {
        openNSUrl("App-Prefs:Privacy&path=LOCATION")
    }
}
