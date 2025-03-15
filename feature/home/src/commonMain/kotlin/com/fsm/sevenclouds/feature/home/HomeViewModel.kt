package com.fsm.sevenclouds.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.PreferenceService
import com.fsm.sevenclouds.core.data.UserRepository
import com.fsm.sevenclouds.core.permissions.model.Permission
import com.fsm.sevenclouds.core.permissions.service.PermissionsService
import kotlinx.coroutines.async



class HomeViewModel(private val userRepository: UserRepository,
                    private val permissionsService: PermissionsService,
                    private val preferenceService: PreferenceService): ViewModel() {
    private val log = Logger.withTag(this::class.simpleName!!)

    suspend fun checkCameraPermission(): Boolean {
        try {
            viewModelScope.async {
                permissionsService.providePermission(Permission.CAMERA)
            }.await()
            return true
        } catch (e: Exception) {
            return false
        }
    }

}
