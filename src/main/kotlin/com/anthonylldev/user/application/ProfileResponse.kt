package com.anthonylldev.user.application

data class ProfileResponse(
    val userId: String,
    val username: String,
    val realName: String?,
    val imageBase64: String?,
    val description: String?,
    val website: String?,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int,
    val isOwnProfile: Boolean,
    val isFollowing: Boolean
)