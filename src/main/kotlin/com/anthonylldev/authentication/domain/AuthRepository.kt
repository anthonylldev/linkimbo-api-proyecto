package com.anthonylldev.authentication.domain

import com.anthonylldev.authentication.application.User

interface AuthRepository {

    suspend fun getUserWhenUsernameAndPasswordMatch(username: String, password: String): User?

}