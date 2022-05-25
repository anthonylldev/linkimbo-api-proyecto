package com.anthonylldev.authentication.application.service.impl

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.user.domain.User

class AuthServiceImpl(
    private val authRepository: AuthRepository
) : AuthService {

    override suspend fun login(username: String, password: String): User? {
        return this.authRepository.getUserWhenUsernameAndPasswordMatch(username, password)
    }

}