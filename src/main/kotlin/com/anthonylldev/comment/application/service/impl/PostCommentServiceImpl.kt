package com.anthonylldev.comment.application.service.impl

import com.anthonylldev.comment.application.service.PostCommentService
import com.anthonylldev.comment.domain.repository.PostCommentRepository
import com.anthonylldev.post.domain.repository.PostRepository
import com.anthonylldev.user.domain.UserRepository

class PostCommentServiceImpl(
    private val postCommentRepository: PostCommentRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) : PostCommentService {

    override suspend fun comment(userId: String, postId: String, comment: String): Boolean {
        userRepository.getUserById(userId) ?: return false
        postRepository.getOneById(postId) ?: return false

        postCommentRepository.insertOne(userId, postId, comment)

        postRepository.updateCommentCount(postId, 1)

        return true
    }
}