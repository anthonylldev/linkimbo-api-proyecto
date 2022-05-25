package com.anthonylldev.authentication.infrastructure.repository

import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.authentication.application.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class AuthRepositoryImpl(
    db: CoroutineDatabase
) : AuthRepository {

    private val user = db.getCollection<User>()

    override suspend fun getUserWhenUsernameAndPasswordMatch(username: String, password: String): User? {
        return this.user.findOne(
            User::username eq username,
            User::password eq password
        )
    }
}