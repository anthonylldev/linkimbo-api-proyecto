package com.anthonylldev.authentication.application.service

import com.anthonylldev.user.domain.User

interface AuthService {

    suspend fun login(username: String, password: String): User?

}