package com.anthonylldev.authentication.infrastructure.repository

import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.user.domain.User
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq
import org.litote.kmongo.json
import org.litote.kmongo.util.idValue

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

    override suspend fun insertUser(username: String, email: String, password: String): User? {
        this.user.insertOne(
            User(
                username = username,
                email = email,
                password = password
        ))

        return this.getUserWhenUsernameAndPasswordMatch(username, password)
    }
}