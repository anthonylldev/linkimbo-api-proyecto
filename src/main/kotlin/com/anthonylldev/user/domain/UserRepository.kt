package com.anthonylldev.user.domain

interface UserRepository {

    suspend fun getUserById(userId: String): User?

}