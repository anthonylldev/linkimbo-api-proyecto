package com.anthonylldev.chat.infrastructure.repository

import com.anthonylldev.chat.domain.model.Message
import com.anthonylldev.chat.domain.repository.MessageRepository
import org.litote.kmongo.ascending
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MessageRepositoryImpl(
    db: CoroutineDatabase
) : MessageRepository {

    private val message = db.getCollection<Message>()

    override suspend fun getAllMessagesByUserId(userId: String): List<Message> {
        return this.message.find(
            Message::userReceiverId eq userId
        ).sort(
            ascending(Message::timestamp)
        ).toList()
    }

    override suspend fun insertMessage(message: Message) {
        this.message.insertOne(message)
    }
}