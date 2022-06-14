package com.anthonylldev.post.domain

interface PostRepository {

    suspend fun insertOne(post: Post): Boolean
    suspend fun findAll(): List<Post>

}