package com.anthonylldev.like.domain.comment.repository

interface CommentLikeRepository {

    suspend fun existRelationship(userId: String, postId: String, commentId: String): Boolean
    suspend fun insertOne(userId: String, postId: String, commentId: String)
    suspend fun deleteOne(userId: String, postId: String, commentId: String)

}