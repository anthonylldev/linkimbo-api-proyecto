package com.anthonylldev.post.application.service.impl

import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.post.domain.Post
import com.anthonylldev.post.domain.PostRepository
import com.anthonylldev.user.domain.User
import com.anthonylldev.user.domain.UserRepository

class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    override suspend fun createPostIfUserExist(post: Post): Boolean {
        this.userRepository.getUserById(post.userId) ?: return false

        this.postRepository.insertOne(post)

        return true
    }

    override suspend fun getAllPostSortByDate(): List<Post> {
        return this.postRepository.findAll()
    }
}