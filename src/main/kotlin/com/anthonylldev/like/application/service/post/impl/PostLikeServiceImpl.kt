package com.anthonylldev.like.application.service.post.impl

import com.anthonylldev.like.application.service.post.PostLikeService
import com.anthonylldev.like.domain.post.repository.PostLikeRepository
import com.anthonylldev.post.domain.repository.PostRepository

class PostLikeServiceImpl(
    private val postLikeRepository: PostLikeRepository,
    private val postRepository: PostRepository
) : PostLikeService {

    override suspend fun like(userId: String, postId: String): Boolean {
        if (this.postLikeRepository.existRelationship(userId, postId)) return false
        this.postLikeRepository.insertOne(userId, postId)

        this.postRepository.updateLikeCount(postId, 1)

        return true
    }

    override suspend fun isLiked(userId: String, postId: String): Boolean {
        return this.postLikeRepository.existRelationship(userId, postId)
    }

    override suspend fun unLike(userId: String, postId: String): Boolean {
        if (!this.postLikeRepository.existRelationship(userId, postId)) return false
        this.postLikeRepository.deleteOne(userId, postId)

        this.postRepository.updateLikeCount(postId, -1)

        return true
    }
}