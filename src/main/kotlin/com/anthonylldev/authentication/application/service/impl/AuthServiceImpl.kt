package com.anthonylldev.authentication.application.service.impl

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.authentication.domain.CreateAccount
import com.anthonylldev.authentication.domain.Login
import com.anthonylldev.user.domain.User

class AuthServiceImpl(
    private val authRepository: AuthRepository
) : AuthService {

    override suspend fun login(login: Login): User? {
        return this.authRepository.getUserWhenUsernameAndPasswordMatch(login.username, login.password)
    }

    override suspend fun createAccount(createAccount: CreateAccount): User? {
        return this.authRepository.insertUser(
            createAccount.username,
            createAccount.email,
            createAccount.password
        )
    }

}