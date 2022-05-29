package com.anthonylldev.follow.domain

interface FollowRepository {

    suspend fun insertOne(userId: String, followedUserId: String): Boolean

    suspend fun deleteOne(userId: String, followedUserId: String): Boolean

    suspend fun isFollowing(userId: String, followedUserId: String): Boolean

}