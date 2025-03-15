package com.fsm.sevenclouds.core.permissions

import com.fsm.sevenclouds.core.permissions.delegate.BluetoothPermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.BluetoothServicePermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.LocationBackgroundPermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.CameraServicePermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.LocationForegroundPermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.LocationServicePermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.PermissionDelegate
import com.fsm.sevenclouds.core.permissions.delegate.StoragePermissionDelegate
import com.fsm.sevenclouds.core.permissions.model.Permission
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual fun platformModule(): Module = module {
    single<PermissionDelegate>(named(Permission.WRITE_STORAGE.name)) {
        StoragePermissionDelegate()
    }

    single<PermissionDelegate>(named(Permission.CAMERA.name)) {
        CameraServicePermissionDelegate()
    }

    single<PermissionDelegate>(named(Permission.BLUETOOTH_SERVICE_ON.name)) {
        BluetoothServicePermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.BLUETOOTH.name)) {
        BluetoothPermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.LOCATION_SERVICE_ON.name)) {
        LocationServicePermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.LOCATION_FOREGROUND.name)) {
        LocationForegroundPermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.LOCATION_BACKGROUND.name)) {
        LocationBackgroundPermissionDelegate(
            locationForegroundPermissionDelegate = get(named(Permission.LOCATION_FOREGROUND.name)),
        )
    }
}
