package com.anthonylldev.comment.application.data

data class PostCommentResponse(
    val id: String,
    val userId: String,
    val postId: String,
    val comment: String
)