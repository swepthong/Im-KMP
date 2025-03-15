package com.fsm.sevenclouds.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)


fun <T> ApiResponse<T>.toResult(): Result<T> {
    return when {
        code == 0 && data != null-> {
            Result.success(data)
        }
        code == 0 && data == null-> {
            Result.failure(Error("data == null"))
        }
        else -> {
            Result.failure(Error(message))
        }
    }
}

@Serializable
data class ApiEmptyResponse(
    val code: Int? = null,
    val message: String? = null
)

fun ApiEmptyResponse.toResult(): Result<Any> {
    return when {
        code == 0 -> {
            Result.success(Any())
        }
        else -> {
            Result.failure(Error(message))
        }
    }
}