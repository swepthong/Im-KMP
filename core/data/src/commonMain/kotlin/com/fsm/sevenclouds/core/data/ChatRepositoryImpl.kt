package com.fsm.sevenclouds.core.data

import com.fsm.sevenclouds.core.data.model.ChatDetail
import com.fsm.sevenclouds.core.data.model.Contact
import com.fsm.sevenclouds.core.data.model.Message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ChatRepositoryImpl(
    private val ioSDispatcher: CoroutineDispatcher
): ChatRepository {

    private var currentChat: Long = 0L

    override fun getChat(): Flow<List<ChatDetail>> {
        val attendees = listOf(Contact(id = "1", name = "啥中", iconUri= ""))
        val list = listOf(
            ChatDetail(
                id = "1",
                attendees = attendees,
                lastMessageId = 1L,
                lastMessage = "哈哈",
                lastMessageTimestamp = 555L
            ),
            ChatDetail(id = "2", attendees = attendees, lastMessageId = 2L, lastMessage = "你是领班", lastMessageTimestamp = 5551L),
            ChatDetail(id = "3", attendees = attendees, lastMessageId = 3L, lastMessage = "地在林", lastMessageTimestamp = 5552L),
            ChatDetail(id = "4", attendees = attendees, lastMessageId = 4L, lastMessage = "要在要要要", lastMessageTimestamp = 5553L),
        )
        return flowOf(list)
    }

    override fun findChat(chatId: Long): Flow<ChatDetail?> {
        val attendees = listOf(Contact(id = "1", name = "啥中", iconUri= ""))
        val chatDetail = ChatDetail(id = "2", attendees = attendees, lastMessageId = 2L, lastMessage = "你是领班", lastMessageTimestamp = 5551L)
        return flowOf(chatDetail)
    }

    override fun findMessages(chatId: Long): Flow<List<Message>> {
        val messageList = listOf(
            Message(1L, 55L, 101L, "大规模夺", null, null, 1009L),
            Message(1L, 55L, 101L, "顶起顺杳桂林顶起权志龙顶起", null, null, 1009L),
        )
        return flowOf(messageList)
    }

    override suspend fun sendMessage(
        chatId: Long,
        text: String,
        mediaUri: String?,
        mediaMimeType: String?
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun clearMessages() {
        TODO("Not yet implemented")
    }

    override fun activateChat(chatId: Long) {
        currentChat = chatId
    }

    override fun deactivateChat(chatId: Long) {
        if (currentChat == chatId) {
            currentChat = 0
        }
    }

    override suspend fun showAsBubble(chatId: Long) {
    }

    override suspend fun canBubble(chatId: Long): Boolean {
        return true
    }

    override fun toggleChatbotSetting() {
    }

}