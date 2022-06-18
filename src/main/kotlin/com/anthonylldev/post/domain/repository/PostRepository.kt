package com.anthonylldev.post.domain.repository

import com.anthonylldev.post.domain.model.Post
import com.anthonylldev.user.application.data.ProfilePostResponse

interface PostRepository {

    suspend fun insertOne(post: Post): Boolean
    suspend fun findAll(): List<Post>
    suspend fun getOneById(postId: String): Post?
    suspend fun updateLikeCount(postId: String, i: Int)
    suspend fun updateCommentCount(postId: String, i: Int)
    suspend fun findAllByUser(userId: String): List<ProfilePostResponse>

}