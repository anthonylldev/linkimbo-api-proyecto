package com.anthonylldev.like.application.service.comment

interface CommentLikeService {
    suspend fun like(userId: String, postId: String, commentId: String): Boolean
    suspend fun unLike(userId: String, postId: String, commentId: String): Boolean
    suspend fun isLiked(userId: String, postId: String, commentId: String): Boolean
}