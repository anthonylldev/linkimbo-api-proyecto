package com.anthonylldev.post.application.service

import com.anthonylldev.post.domain.Post

interface PostService {

    suspend fun createPostIfUserExist(post: Post): Boolean
    suspend fun getAllPostSortByDate(): List<Post>

}
