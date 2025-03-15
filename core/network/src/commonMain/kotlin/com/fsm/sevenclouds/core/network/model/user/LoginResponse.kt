package com.fsm.sevenclouds.core.network.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("token") var token: String,
    @SerialName("expire") var expire: Int,
)

@Serializable
data class CodeInfoResponse(
    @SerialName("name") var name: String,
    @SerialName("icon") var icon: String,
    @SerialName("code") var code: String,
    @SerialName("type") var type: String,
)