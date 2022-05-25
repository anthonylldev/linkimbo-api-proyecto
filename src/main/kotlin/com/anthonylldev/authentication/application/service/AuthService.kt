package com.anthonylldev.authentication.application.service

import com.anthonylldev.authentication.application.User

interface AuthService {

    suspend fun login(username: String, password: String): User?

}