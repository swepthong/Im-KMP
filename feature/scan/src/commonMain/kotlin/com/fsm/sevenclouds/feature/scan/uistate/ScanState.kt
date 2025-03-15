package com.fsm.sevenclouds.feature.scan.uistate



sealed class ScanState {
    data object Idle : ScanState()
    data class Confirm(val code: String) : ScanState()
    data object Success : ScanState()
    data class Error(val message: String) : ScanState()
}