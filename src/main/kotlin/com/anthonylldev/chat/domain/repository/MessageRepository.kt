package com.anthonylldev.chat.domain.repository

import com.anthonylldev.chat.domain.model.Message

interface MessageRepository {

    suspend fun getAllMessagesByUserId(userId: String): List<Message>
    suspend fun insertMessage(message: Message)
}