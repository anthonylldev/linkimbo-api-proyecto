package com.anthonylldev.comment.domain.repository

import com.anthonylldev.comment.domain.model.PostComment

interface PostCommentRepository {
    suspend fun insertOne(userId: String, postId: String, postComment: PostComment)
    suspend fun getCommentsById(postId: String): List<PostComment>
    suspend fun updateLikeCount(commentId: String, i: Int)

}