package com.anthonylldev.like.application.service.post

interface PostLikeService {
    suspend fun like(userId: String, postId: String): Boolean
    suspend fun unLike(userId: String, postId: String): Boolean
    suspend fun isLiked(userId: String, postId: String): Boolean
}