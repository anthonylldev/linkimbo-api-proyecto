package com.anthonylldev.comment.domain.repository

import com.anthonylldev.comment.domain.model.PostComment

interface PostCommentRepository {
    suspend fun insertOne(userId: String, postId: String, comment: String)
    suspend fun getCommentsById(postId: String): List<PostComment>
}