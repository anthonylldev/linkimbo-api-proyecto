package com.anthonylldev.user.domain

interface UserRepository {

    suspend fun getUserById(userId: String): User?
    suspend fun incrementFollow(userId: String, followedUserId: String)
    suspend fun reduceFollow(userId: String, followedUserId: String)
    suspend fun updateUser(request: User, userId: String): Boolean
}