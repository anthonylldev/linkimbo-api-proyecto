package com.anthonylldev.post.application.service.impl

import com.anthonylldev.post.application.data.PostRequest
import com.anthonylldev.post.application.data.PostResponse
import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.post.domain.model.Post
import com.anthonylldev.post.domain.repository.PostRepository
import com.anthonylldev.user.domain.UserRepository

class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    override suspend fun createPostIfUserExist(postRequest: PostRequest): Boolean {
        this.userRepository.getUserById(postRequest.userId) ?: return false

        this.postRepository.insertOne(
            Post(
                userId = postRequest.userId,
                imageBase64 = postRequest.imageBase64,
                description = postRequest.description,
                timestamp = postRequest.timestamp
            )
        )

        return true
    }

    override suspend fun getAllPostSortByDate(): List<PostResponse> {
        val post: MutableList<PostResponse> = mutableListOf()

        this.postRepository.findAll().forEach { request ->
            this.userRepository.getUserById(request.userId)?.let { user ->
                post.add(
                    PostResponse(
                        id = request.id,
                        user = user,
                        imageBase64 = request.imageBase64,
                        description = request.description,
                        likeCount = request.likeCount,
                        isLiked = false,
                        commentCount = request.commentCount,
                        timestamp = request.timestamp
                    )
                )
            }
        }

        return post
    }

    override suspend fun getPost(postId: String): PostResponse? {

        this.postRepository.getOneById(postId)?.let { post ->
            this.userRepository.getUserById(post.userId)?.let { user ->
                return PostResponse(
                    id = post.id,
                    user = user,
                    imageBase64 = post.imageBase64,
                    description = post.description,
                    likeCount = post.likeCount,
                    isLiked = false,
                    commentCount = post.commentCount,
                    timestamp = post.timestamp
                )
            }
            return null
        }
        return null
    }
}