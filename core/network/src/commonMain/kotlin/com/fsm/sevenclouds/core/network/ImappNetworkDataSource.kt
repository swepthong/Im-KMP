package com.fsm.sevenclouds.core.network

import com.fsm.sevenclouds.core.network.model.ApiEmptyResponse
import com.fsm.sevenclouds.core.network.model.ApiResponse
import com.fsm.sevenclouds.core.network.model.user.CodeInfoResponse
import com.fsm.sevenclouds.core.network.model.user.LoginResponse

/**
 */
interface SevencloudsNetworkDataSource {
    suspend fun login(name: String, password: String): ApiResponse<LoginResponse>
    suspend fun submitCodeInfo(code: String): ApiResponse<CodeInfoResponse>
    suspend fun codeConfirm(code: String): ApiEmptyResponse
}