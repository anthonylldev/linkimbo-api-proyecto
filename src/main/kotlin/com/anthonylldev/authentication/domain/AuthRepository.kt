package com.anthonylldev.authentication.domain

import com.anthonylldev.user.domain.User

interface AuthRepository {

    suspend fun getUserWhenUsernameAndPasswordMatch(username: String, password: String): User?
    suspend fun insertUser(username: String, email: String, password: String): User?

}