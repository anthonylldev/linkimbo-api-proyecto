package com.anthonylldev.user.application.service

import com.anthonylldev.user.application.ProfileResponse
import com.anthonylldev.user.domain.User

interface UserService {

    suspend fun loadProfile(userId: String, callerUserId: String): ProfileResponse?
    suspend fun updateUser(request: User, userId: String): User?
    suspend fun getUser(userId: String): User?

}