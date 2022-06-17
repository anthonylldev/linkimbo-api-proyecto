package com.anthonylldev.comment.application.data

import com.anthonylldev.user.domain.User

data class PostCommentResponse(
    val id: String,
    val user: User,
    val timestamp : Long,
    val comment: String,
    val likeCount: Int,
    val isLiked: Boolean
)