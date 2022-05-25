package com.anthonylldev.user.infrastructure.repository

import com.anthonylldev.user.domain.User
import com.anthonylldev.user.domain.UserRepository
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserRepositoryImpl(
    db: CoroutineDatabase
) : UserRepository {

    private val user = db.getCollection<User>()

    override suspend fun getUserById(userId: String): User? {
        return this.user.findOneById(ObjectId(userId))
    }
}