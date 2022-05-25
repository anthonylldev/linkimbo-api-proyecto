package com.anthonylldev.user.application.service

import com.anthonylldev.user.application.ProfileResponse

interface UserService {

    suspend fun loadProfile(userId: String, callerUserId: String): ProfileResponse?

}