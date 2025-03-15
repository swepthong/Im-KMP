package com.fsm.sevenclouds.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.DataStoreKeys.EXPIRE
import com.fsm.sevenclouds.core.common.DataStoreKeys.NAME
import com.fsm.sevenclouds.core.common.DataStoreKeys.PASSWORD
import com.fsm.sevenclouds.core.common.DataStoreKeys.SERVER
import com.fsm.sevenclouds.core.common.DataStoreKeys.TOKEN
import com.fsm.sevenclouds.core.common.PreferenceService

import com.fsm.sevenclouds.core.data.UserRepository
import com.fsm.sevenclouds.feature.login.uistate.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


class LoginViewModel(
    private val userRepository: UserRepository,
    private val preferenceService: PreferenceService
) : ViewModel() {
    private val log = Logger.withTag(this::class.simpleName!!)

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState>
        get() = _loginState

    fun performLogin(username: String, password: String) = viewModelScope.launch {
        log.d("perform username: $username  password: $password")
        val result = userRepository.login(username, password)
        log.d(result.toString())
        if(result.isSuccess) {
            preferenceService.storeData(TOKEN, result.getOrNull()?.token.orEmpty())
            preferenceService.storeData(NAME, username)
            preferenceService.storeData(PASSWORD, password)
            preferenceService.storeIntData(EXPIRE, result.getOrNull()?.expire ?: 0)
            _loginState.value = LoginState.Success
        }
    }

    fun getUserName(): String = preferenceService.retrieveData(NAME).orEmpty()
    fun getPassword(): String = preferenceService.retrieveData(PASSWORD).orEmpty()
    fun getServer(): String = preferenceService.retrieveData(SERVER).orEmpty()

    fun checkLogin() = viewModelScope.launch {
        val token = preferenceService.retrieveData(TOKEN)
        val expire = preferenceService.retrieveIntData(EXPIRE)
        val currentInstant: Instant = Clock.System.now()
        val currentSecond = currentInstant.toEpochMilliseconds().toInt()
        log.d("checkLogin token: $token")
        log.d("checkLogin currentInstant: $currentInstant")
        log.d("checkLogin expire: $expire")
        log.d("checkLogin currentSecond: $currentSecond")
        if(!token.isNullOrBlank() && currentSecond < expire) {
           // _loginState.value = LoginState.Success
        }
    }

    fun saveServer(server: String) = viewModelScope.launch {
        preferenceService.storeData(SERVER, server)
    }
}
