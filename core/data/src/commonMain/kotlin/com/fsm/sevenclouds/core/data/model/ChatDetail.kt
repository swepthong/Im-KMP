package com.fsm.sevenclouds.core.data.model

data class ChatDetail(
    val id: String,
    val attendees: List<Contact>,
    val lastMessageId: Long,
    val lastMessage: String,
    val lastMessageTimestamp: Long
)
