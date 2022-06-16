package com.anthonylldev.like.infrastructure.repository

import com.anthonylldev.like.domain.model.PostLike
import com.anthonylldev.like.domain.repository.PostLikeRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PostLikeRepositoryImpl(
    db: CoroutineDatabase
) : PostLikeRepository {

    private val postLike = db.getCollection<PostLike>()

    override suspend fun existRelationship(userId: String, postId: String): Boolean {
        return this.postLike.findOne(
            PostLike::userId eq userId,
            PostLike::postId eq postId
        ) != null
    }

    override suspend fun deleteOne(userId: String, postId: String) {
        this.postLike.deleteOne(
            PostLike::userId eq userId,
            PostLike::postId eq postId
        )
    }

    override suspend fun insertOne(userId: String, postId: String) {
        this.postLike.insertOne(
            PostLike(
                userId = userId,
                postId = postId
            )
        )
    }
}