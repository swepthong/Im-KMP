package com.fsm.sevenclouds.feature.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.PreferenceService
import com.fsm.sevenclouds.core.data.ChatRepository
import com.fsm.sevenclouds.core.permissions.model.Permission
import com.fsm.sevenclouds.core.permissions.service.PermissionsService
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class MessageViewModel(
    private val repository: ChatRepository,
    private val permissionsService: PermissionsService,
    private val preferenceService: PreferenceService
) : ViewModel() {
    private val log = Logger.withTag(this::class.simpleName!!)

    private fun <T> Flow<T>.stateInUi(
        initialValue: T,
    ): StateFlow<T> {
        return stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), initialValue)
    }

    val chats = repository.getChat().stateInUi(emptyList())

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
