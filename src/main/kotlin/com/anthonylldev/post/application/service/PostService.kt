package com.anthonylldev.post.application.service

import com.anthonylldev.post.application.dto.PostDto

interface PostService {

    suspend fun createPostIfUserExist(postDto: PostDto): Boolean
    suspend fun getAllPostSortByDate(): List<PostDto>
    suspend fun getPost(postId: String): PostDto?

}
