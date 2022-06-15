package com.anthonylldev.post.domain.repository

import com.anthonylldev.post.domain.model.Post

interface PostRepository {

    suspend fun insertOne(post: Post): Boolean
    suspend fun findAll(): List<Post>
    suspend fun getOneById(postId: String): Post?

}