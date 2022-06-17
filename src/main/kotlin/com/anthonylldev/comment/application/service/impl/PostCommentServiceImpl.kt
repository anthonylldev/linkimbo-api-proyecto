package com.anthonylldev.comment.application.service.impl

import com.anthonylldev.comment.application.data.PostCommentResponse
import com.anthonylldev.comment.application.service.PostCommentService
import com.anthonylldev.comment.domain.model.PostComment
import com.anthonylldev.comment.domain.repository.PostCommentRepository
import com.anthonylldev.post.domain.model.Post
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

    override suspend fun getAllPostCommentsByPost(postId: String): List<PostCommentResponse>? {
        postRepository.getOneById(postId) ?: return null

        val postComments: MutableList<PostCommentResponse> = mutableListOf()

        postCommentRepository.getCommentsById(postId).forEach { comment ->
            userRepository.getUserById(comment.userId)?.let { user ->
                postComments.add(PostCommentResponse(
                    id = comment.id,
                    user = user,
                    comment = comment.comment
                ))
            }
        }

        return postComments
    }
}