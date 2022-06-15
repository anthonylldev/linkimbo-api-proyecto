package com.anthonylldev.post.application.service.impl

import com.anthonylldev.post.application.dto.PostDto
import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.post.domain.model.Post
import com.anthonylldev.post.domain.repository.PostRepository
import com.anthonylldev.user.domain.UserRepository

class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    override suspend fun createPostIfUserExist(postDto: PostDto): Boolean {
        this.userRepository.getUserById(postDto.user.id) ?: return false

        this.postRepository.insertOne(
            Post(
            id = postDto.id,
            userId = postDto.user.id,
            imageBase64 = postDto.imageBase64,
            description = postDto.description,
            likeCount = postDto.likeCount,
            timestamp = postDto.timestamp
        )
        )

        return true
    }

    override suspend fun getAllPostSortByDate(): List<PostDto> {
        val post: MutableList<PostDto> = mutableListOf()

        this.postRepository.findAll().forEach { request ->
            this.userRepository.getUserById(request.userId)?.let { user ->
                post.add(PostDto(
                    id = request.id,
                    user = user,
                    imageBase64 = request.imageBase64,
                    description = request.description,
                    likeCount = request.likeCount,
                    commentCount = request.commentCount,
                    timestamp = request.timestamp
                ))
            }
        }

        return post
    }
}