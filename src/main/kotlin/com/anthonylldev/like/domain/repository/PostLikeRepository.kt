package com.anthonylldev.like.domain.repository

interface PostLikeRepository {
    suspend fun existRelationship(userId: String, postId: String): Boolean
    suspend fun insertOne(userId: String, postId: String)
    suspend fun deleteOne(userId: String, postId: String)
}