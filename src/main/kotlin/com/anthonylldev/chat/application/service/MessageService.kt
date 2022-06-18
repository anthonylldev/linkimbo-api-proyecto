package com.anthonylldev.chat.application.service

import com.anthonylldev.chat.domain.model.Message

interface MessageService {

    suspend fun getAllMessagesByUserId(userId: String): List<Message>?
    suspend fun insertMessage(message: Message)

}