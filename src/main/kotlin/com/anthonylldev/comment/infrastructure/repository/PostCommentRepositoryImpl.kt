package com.anthonylldev.comment.infrastructure.repository

import com.anthonylldev.comment.domain.model.PostComment
import com.anthonylldev.comment.domain.repository.PostCommentRepository
import com.anthonylldev.like.domain.comment.model.CommentLike
import com.anthonylldev.post.domain.model.Post
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.inc

class PostCommentRepositoryImpl(
    db: CoroutineDatabase
) : PostCommentRepository {

    private val postComment = db.getCollection<PostComment>()

    override suspend fun insertOne(userId: String, postId: String, request: PostComment) {
        this.postComment.insertOne(request)
    }

    override suspend fun getCommentsById(postId: String): List<PostComment> {
        return this.postComment.find(
            PostComment::postId eq postId
        ).toList()
    }

    override suspend fun updateLikeCount(commentId: String, i: Int) {
        postComment.updateOneById(
            commentId,
            inc(PostComment::likeCount, i)
        )
    }
}