package com.anthonylldev.chat.application.service.impl

import com.anthonylldev.chat.application.service.MessageService
import com.anthonylldev.chat.domain.model.Message
import com.anthonylldev.chat.domain.repository.MessageRepository
import com.anthonylldev.user.domain.UserRepository

class MessageServiceImpl(
    private val messageRepository: MessageRepository,
    private val userRepository: UserRepository
) : MessageService {

    override suspend fun getAllMessagesByUserId(userId: String): List<Message>? {
        this.userRepository.getUserById(userId) ?: return null

        return messageRepository.getAllMessagesByUserId(userId)
    }

    override suspend fun insertMessage(message: Message) {
        this.messageRepository.insertMessage(message)
    }
}