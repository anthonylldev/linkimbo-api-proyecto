package com.anthonylldev.authentication.application.service

import com.anthonylldev.authentication.application.CreateAccount
import com.anthonylldev.authentication.application.Login
import com.anthonylldev.user.domain.User

interface AuthService {

    suspend fun login(login: Login): User?

    suspend fun createAccount(createAccount: CreateAccount): User?

}