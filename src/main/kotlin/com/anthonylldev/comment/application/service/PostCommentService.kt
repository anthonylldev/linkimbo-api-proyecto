package com.anthonylldev.comment.application.service

import com.anthonylldev.comment.application.data.PostCommentResponse

interface PostCommentService {
    suspend fun comment(userId: String, postId: String, comment: String): Boolean
    suspend fun getAllPostCommentsByPost(postId: String): List<PostCommentResponse>?
}