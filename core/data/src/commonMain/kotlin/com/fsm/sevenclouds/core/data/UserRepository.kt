package com.fsm.sevenclouds.core.data

import com.fsm.sevenclouds.core.network.model.ApiResponse
import com.fsm.sevenclouds.core.network.model.user.CodeInfoResponse
import com.fsm.sevenclouds.core.network.model.user.LoginResponse


/**
 * Data layer interface for the arts
 */
interface UserRepository {
    suspend fun login(name: String, password: String): Result<LoginResponse>
    suspend fun submitCodeInfo(code: String): Result<CodeInfoResponse>
    suspend fun codeConfirm(code: String): Result<Any>
}
