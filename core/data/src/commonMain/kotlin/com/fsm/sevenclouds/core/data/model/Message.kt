package com.fsm.sevenclouds.core.data.model

data class Message(
    val id: Long,
    val chatId: Long,
    val senderId: Long,
    val text: String,
    val mediaUri: String?,
    val mediaMimeType: String?,
    val timestamp: Long,
) {
    val isIncoming: Boolean
        get() = senderId != 0L
}
