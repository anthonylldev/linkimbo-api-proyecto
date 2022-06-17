package com.anthonylldev.comment.application.data

import com.anthonylldev.user.domain.User

data class PostCommentResponse(
    val id: String,
    val user: User,
    val comment: String
)