package com.fsm.sevenclouds.core.network.ktor

import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.DataStoreKeys.SERVER
import com.fsm.sevenclouds.core.common.DataStoreKeys.TOKEN
import com.fsm.sevenclouds.core.common.PreferenceService
import com.fsm.sevenclouds.core.network.SevencloudsNetworkDataSource
import com.fsm.sevenclouds.core.network.model.ApiEmptyResponse
import com.fsm.sevenclouds.core.network.model.ApiResponse
import com.fsm.sevenclouds.core.network.model.user.CodeInfoResponse
import com.fsm.sevenclouds.core.network.model.user.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.path
import io.ktor.http.takeFrom


private const val IMAPP_HOST = "http://10.168.2.110:5001"
private const val IMAPP_PORT = 5001
private const val IMAPP_PATH = "api"


class KtorSevencloudsNetwork(
    private val client: HttpClient,
    private val preferenceService: PreferenceService
) : SevencloudsNetworkDataSource {
    private val log = Logger.withTag(this::class.simpleName!!)

    override suspend fun login(name: String, password: String): ApiResponse<LoginResponse> {
        val serverHost = preferenceService.retrieveData(SERVER).takeUnless { it.isNullOrBlank() } ?: IMAPP_HOST
        return client.submitFormWithBinaryData(
            url = LOGIN,
            formData = formData {
                append("username", name)
                append("password", password)
            }
        ) {
            url {
                takeFrom(serverHost)
                path(IMAPP_PATH, LOGIN)
            }
        }.body<ApiResponse<LoginResponse>>()
    }

    override suspend fun submitCodeInfo(code: String): ApiResponse<CodeInfoResponse> {
        val serverHost = preferenceService.retrieveData(SERVER).takeUnless { it.isNullOrBlank() } ?: IMAPP_HOST
        val token = preferenceService.retrieveData(TOKEN).orEmpty()
        return client.submitFormWithBinaryData(
            url = CODE_INFO,
            formData = formData {
                append("data", code)
            }
        ){
            url {
                takeFrom(serverHost)
                path(IMAPP_PATH, CODE_INFO)
            }
            header(TOKEN_KEY, token)
        }.body<ApiResponse<CodeInfoResponse>>()
    }

    override suspend fun codeConfirm(code: String): ApiEmptyResponse {
        val serverHost = preferenceService.retrieveData(SERVER).takeUnless { it.isNullOrBlank() } ?: IMAPP_HOST
        val token = preferenceService.retrieveData(TOKEN).orEmpty()
           val bodyResponse = client.submitFormWithBinaryData(
               url = CODE_CONFIRM,
               formData = formData {
                   append("code", code)
               }
           ) {
               url {
                   takeFrom(serverHost)
                   path(IMAPP_PATH, CODE_CONFIRM)
               }
               header(TOKEN_KEY, token)
           }
           log.d("codeConfirm bodyResponse:$bodyResponse")
           val body = bodyResponse.body<ApiEmptyResponse>()
           return body
    }

    companion object {
        private const val CODE_INFO = "code/info"
        private const val CODE_CONFIRM = "code/confirm"
        private const val LOGIN = "login"
        private const val TOKEN_KEY = "token"
    }

}