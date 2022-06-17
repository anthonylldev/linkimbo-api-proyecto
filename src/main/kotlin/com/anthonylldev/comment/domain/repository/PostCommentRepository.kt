package com.anthonylldev.comment.domain.repository

interface PostCommentRepository {
    suspend fun insertOne(userId: String, postId: String, comment: String)
}