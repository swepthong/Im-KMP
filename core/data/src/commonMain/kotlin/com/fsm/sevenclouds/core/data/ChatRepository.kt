package com.fsm.sevenclouds.core.data

import com.fsm.sevenclouds.core.data.model.ChatDetail
import com.fsm.sevenclouds.core.data.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getChat(): Flow<List<ChatDetail>>
    fun findChat(chatId: Long): Flow<ChatDetail?>
    fun findMessages(chatId: Long): Flow<List<Message>>
    suspend fun sendMessage(
        chatId: Long,
        text: String,
        mediaUri: String?,
        mediaMimeType: String?,
    )

    suspend fun clearMessages()
    fun activateChat(chatId: Long)
    fun deactivateChat(chatId: Long)
    suspend fun showAsBubble(chatId: Long)
    suspend fun canBubble(chatId: Long): Boolean
    fun toggleChatbotSetting()
}