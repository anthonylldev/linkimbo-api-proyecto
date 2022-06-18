package com.anthonylldev.like.infrastructure.repository.comment

import com.anthonylldev.like.domain.comment.model.CommentLike
import com.anthonylldev.like.domain.comment.repository.CommentLikeRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CommentLikeRepositoryImpl(
    db: CoroutineDatabase
) : CommentLikeRepository {

    private val commentLike = db.getCollection<CommentLike>()

    override suspend fun existRelationship(userId: String, postId: String, commentId: String): Boolean {
        return this.commentLike.findOne(
            CommentLike::userId eq userId,
            CommentLike::postId eq postId,
            CommentLike::commentId eq commentId
        ) != null
    }

    override suspend fun insertOne(userId: String, postId: String, commentId: String) {
        this.commentLike.insertOne(
            CommentLike(
                userId = userId,
                postId = postId,
                commentId = commentId
            )
        )
    }

    override suspend fun deleteOne(userId: String, postId: String, commentId: String) {
        this.commentLike.deleteOne(
            CommentLike::userId eq userId,
            CommentLike::postId eq postId,
            CommentLike::commentId eq commentId
        )
    }
}