package com.anthonylldev.comment.infrastructure.repository

import com.anthonylldev.comment.application.data.PostCommentResponse
import com.anthonylldev.comment.domain.model.PostComment
import com.anthonylldev.comment.domain.repository.PostCommentRepository
import com.anthonylldev.post.domain.model.Post
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

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

    override suspend fun getCommentsById(postId: String): List<PostComment> {
        return this.postComment.find(
            PostComment::postId eq postId
        ).toList()
    }
}