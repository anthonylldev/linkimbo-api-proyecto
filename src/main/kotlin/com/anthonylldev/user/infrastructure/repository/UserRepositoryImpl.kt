package com.anthonylldev.user.infrastructure.repository

import com.anthonylldev.user.domain.User
import com.anthonylldev.user.domain.UserRepository
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.inc

class UserRepositoryImpl(
    db: CoroutineDatabase
) : UserRepository {

    private val user = db.getCollection<User>()

    override suspend fun getUserById(userId: String): User? {
        return this.user.findOneById(ObjectId(userId))
    }

    override suspend fun incrementFollow(userId: String, followedUserId: String) {
        user.updateOneById(
            ObjectId(userId),
            inc(User::followingCount, 1)
        )

        user.updateOneById(
            ObjectId(followedUserId),
            inc(User::followerCount, 1)
        )
    }

    override suspend fun reduceFollow(userId: String, followedUserId: String) {
        user.updateOneById(
            ObjectId(userId),
            inc(User::followingCount,-1)
        )

        user.updateOneById(
            ObjectId(followedUserId),
            inc(User::followerCount, -1)
        )
    }
}