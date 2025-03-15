package com.fsm.sevenclouds.feature.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.PreferenceService
import com.fsm.sevenclouds.core.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.fsm.sevenclouds.feature.scan.uistate.ScanState.Idle
import com.fsm.sevenclouds.feature.scan.uistate.ScanState
import kotlinx.coroutines.launch

class ScanViewModel(
    private val userRepository: UserRepository,
    private val preferenceService: PreferenceService
) : ViewModel()  {

    private val log = Logger.withTag(this::class.simpleName!!)

    private val _scanState = MutableStateFlow<ScanState>(Idle)
    val scanState: StateFlow<ScanState>
        get() = _scanState

    fun submitCodeInfo(code: String) = viewModelScope.launch {
        log.d("submitCodeInfo:   code: $code")

        val result = userRepository.submitCodeInfo(code)
        log.d(result.toString())
        if(result.isSuccess) {
            _scanState.value = ScanState.Confirm(result.getOrNull()?.code.orEmpty())
        } else {
            _scanState.value = ScanState.Error(result.exceptionOrNull()?.message.orEmpty())
        }
    }

    fun codeConfirm(code: String) = viewModelScope.launch {
        log.d("codeConfirm:   code: $code")
        val result = userRepository.codeConfirm(code)
        log.d(result.toString())
        if(result.isSuccess) {
            _scanState.value = ScanState.Success
        } else {
            _scanState.value = ScanState.Error(result.exceptionOrNull()?.message.orEmpty())
        }
    }
}