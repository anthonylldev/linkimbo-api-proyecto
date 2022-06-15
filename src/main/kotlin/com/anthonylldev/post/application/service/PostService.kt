package com.anthonylldev.post.application.service

import com.anthonylldev.post.application.data.PostRequest
import com.anthonylldev.post.application.data.PostResponse

interface PostService {

    suspend fun createPostIfUserExist(postRequest: PostRequest): Boolean
    suspend fun getAllPostSortByDate(): List<PostResponse>
    suspend fun getPost(postId: String): PostResponse?

}
