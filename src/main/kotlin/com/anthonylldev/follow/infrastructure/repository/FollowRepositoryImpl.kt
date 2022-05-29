package com.anthonylldev.follow.infrastructure.repository

import com.anthonylldev.follow.application.Follow
import com.anthonylldev.follow.domain.FollowRepository
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.util.idValue

class FollowRepositoryImpl(
    db: CoroutineDatabase
) : FollowRepository {

    private val follow = db.getCollection<Follow>()

    override suspend fun insertOne(userId: String, followedUserId: String): Boolean {
        return this.follow.insertOne(Follow(
            userId = userId,
            followedUserId = followedUserId
        )).idValue != null
    }

    override suspend fun deleteOne(userId: String, followedUserId: String): Boolean {
        return this.follow.deleteOne(and(
            Follow::userId eq userId,
            Follow::followedUserId eq followedUserId,
        )).deletedCount > 0
    }

    override suspend fun isFollowing(userId: String, followedUserId: String): Boolean {
        val result: Follow? = this.follow.findOne(and(
            Follow::userId eq userId,
            Follow::followedUserId eq followedUserId
        ))

        return result != null
    }
}