package com.anthonylldev.like.application.service.comment.impl

import com.anthonylldev.comment.domain.repository.PostCommentRepository
import com.anthonylldev.like.application.service.comment.CommentLikeService
import com.anthonylldev.like.domain.comment.repository.CommentLikeRepository

class CommentLikeServiceImpl(
    private val commentLikeRepository: CommentLikeRepository,
    private val postCommentRepository: PostCommentRepository
) : CommentLikeService {

    override suspend fun like(userId: String, postId: String, commentId: String): Boolean {
        if (this.commentLikeRepository.existRelationship(userId, postId, commentId)) return false
        this.commentLikeRepository.insertOne(userId, postId, commentId)

        this.postCommentRepository.updateLikeCount(commentId, 1)

        return true
    }

    override suspend fun isLiked(userId: String, postId: String, commentId: String): Boolean {
        return this.commentLikeRepository.existRelationship(userId, postId, commentId)
    }

    override suspend fun unLike(userId: String, postId: String, commentId: String): Boolean {
        if (!this.commentLikeRepository.existRelationship(userId, postId, commentId)) return false
        this.commentLikeRepository.deleteOne(userId, postId, commentId)

        this.postCommentRepository.updateLikeCount(commentId, -1)

        return true
    }
}