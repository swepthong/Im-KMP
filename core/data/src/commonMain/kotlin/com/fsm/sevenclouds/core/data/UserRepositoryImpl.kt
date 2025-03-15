package com.fsm.sevenclouds.core.data

import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.network.SevencloudsNetworkDataSource
import com.fsm.sevenclouds.core.network.model.toResult
import com.fsm.sevenclouds.core.network.model.user.CodeInfoResponse
import com.fsm.sevenclouds.core.network.model.user.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val sevencloudsDataSource: SevencloudsNetworkDataSource,
    private val ioSDispatcher: CoroutineDispatcher
) : UserRepository {
    private val log = Logger.withTag(this::class.simpleName!!)

    override suspend fun login(name: String, password: String): Result<LoginResponse> = withContext(ioSDispatcher) {
         try {
            sevencloudsDataSource.login(name, password).toResult()
        } catch (e: Exception) {
            log.e(e) { "login Error ${e.message}" }
            Result.failure(e)
        }
    }


    override suspend fun submitCodeInfo(code: String): Result<CodeInfoResponse> = withContext(ioSDispatcher) {
        try {
            sevencloudsDataSource.submitCodeInfo(code).toResult()
        } catch (e: Exception) {
            log.e(e) { "submitCodeInfo Error ${e.message}" }
            Result.failure(e)
        }
    }

    override suspend fun codeConfirm(code: String): Result<Any> = withContext(ioSDispatcher) {
        try {
           sevencloudsDataSource.codeConfirm(code).toResult()
        } catch (e: Exception) {
            log.e(e) { "codeConfirm Error ${e.message}" }
            Result.failure(e)
        }
    }

}

