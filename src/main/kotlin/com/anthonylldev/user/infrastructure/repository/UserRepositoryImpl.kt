package com.anthonylldev.user.infrastructure.repository

import com.anthonylldev.user.domain.User
import com.anthonylldev.user.domain.UserRepository
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.inc
import org.litote.kmongo.util.idValue

class UserRepositoryImpl(
    db: CoroutineDatabase
) : UserRepository {

    private val user = db.getCollection<User>()

    override suspend fun getUserById(userId: String): User? {
        return this.user.findOneById(userId)
    }

    override suspend fun updateUser(request: User, userId: String): Boolean {
        return this.user.updateOneById(
            id = userId,
            update = User(
                id = userId,
                username = request.username,
                email = request.email,
                password = request.password,
                realName = request.realName,
                imageBase64 = request.imageBase64,
                description = request.description,
                website = request.website,
                followerCount = request.followerCount,
                followingCount = request.followingCount,
                postCount = request.postCount,
            )

        ).modifiedCount > 0
    }

    override suspend fun incrementFollow(userId: String, followedUserId: String) {
        user.updateOneById(
            userId,
            inc(User::followingCount, 1)
        )

        user.updateOneById(
            followedUserId,
            inc(User::followerCount, 1)
        )
    }

    override suspend fun reduceFollow(userId: String, followedUserId: String) {
        user.updateOneById(
            userId,
            inc(User::followingCount, -1)
        )

        user.updateOneById(
            followedUserId,
            inc(User::followerCount, -1)
        )
    }
}