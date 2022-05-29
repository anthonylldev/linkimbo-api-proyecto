package com.anthonylldev.follow.application.service

interface FollowService {
    suspend fun followUserIfExists(userId: String, followedUserId: String): Boolean
    suspend fun unFollowUserIfExists(userId: String, unFollowedUserId: String): Boolean
}