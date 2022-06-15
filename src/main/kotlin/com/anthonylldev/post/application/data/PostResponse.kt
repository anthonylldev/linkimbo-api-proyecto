package com.anthonylldev.post.application.data

import com.anthonylldev.user.domain.User

data class PostResponse(
    val id: String?,
    val user: User,
    val imageBase64: String,
    val description: String,
    val likeCount: Int,
    val isLiked: Boolean,
    val commentCount: Int = 0,
    val timestamp: Long
)
