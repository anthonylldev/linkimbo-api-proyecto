package com.anthonylldev.comment.infrastructure.repository

import com.anthonylldev.comment.domain.model.PostComment
import com.anthonylldev.comment.domain.repository.PostCommentRepository
import org.litote.kmongo.coroutine.CoroutineDatabase

class PostCommentRepositoryImpl(
    db: CoroutineDatabase
) : PostCommentRepository {

    private val postComment = db.getCollection<PostComment>()

    override suspend fun insertOne(userId: String, postId: String, comment: String) {
        this.postComment.insertOne(PostComment(
            userId = userId,
            postId = postId,
            comment = comment
        ))
    }
}