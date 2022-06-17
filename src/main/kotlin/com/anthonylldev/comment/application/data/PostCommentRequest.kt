package com.anthonylldev.comment.application.data

data class PostCommentRequest(
    val userId: String,
    val comment: String,
    val timestamp: Long
)
