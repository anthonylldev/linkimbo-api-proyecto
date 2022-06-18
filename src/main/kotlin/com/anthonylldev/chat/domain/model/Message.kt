package com.anthonylldev.chat.domain.model

data class Message(
    val userEmitterId: String,
    val userReceiverId: String,
    val message: String,
    val timestamp: Long
)
