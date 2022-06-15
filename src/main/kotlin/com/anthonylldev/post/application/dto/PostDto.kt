package com.anthonylldev.post.application.dto

import com.anthonylldev.user.domain.User

data class PostDto(
    val id: String?,
    val user: User,
    val imageBase64: String,
    val description: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val timestamp: Long
)
