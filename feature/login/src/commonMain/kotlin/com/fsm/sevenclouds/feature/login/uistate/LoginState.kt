package com.fsm.sevenclouds.feature.login.uistate

sealed class LoginState {
    data object Idle : LoginState() // 初始状态
    data object Loading : LoginState() // 加载状态
    data object Success : LoginState() // 登录成功
    data class Error(val message: String) : LoginState() // 登录失败
}