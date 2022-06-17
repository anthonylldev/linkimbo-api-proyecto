package com.anthonylldev.comment.application.service

interface PostCommentService {
    suspend fun comment(userId: String, postId: String, comment: String): Boolean
}