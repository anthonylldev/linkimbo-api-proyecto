package com.anthonylldev.post.application.service

import com.anthonylldev.post.application.data.PostRequest
import com.anthonylldev.post.application.data.PostResponse

interface PostService {

    suspend fun createPostIfUserExist(postRequest: PostRequest): Boolean
    suspend fun getAllPostSortByDate(currentUserId: String): List<PostResponse>
    suspend fun getPost(currentUserId: String, postId: String): PostResponse?
    suspend fun updateLikeCount(postId: String, i: Int)

}
