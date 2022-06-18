package com.anthonylldev.post.infrastructure.repository

import com.anthonylldev.post.domain.model.Post
import com.anthonylldev.post.domain.repository.PostRepository
import com.anthonylldev.user.application.data.ProfilePostResponse
import com.anthonylldev.user.domain.User
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.inc
import org.litote.kmongo.util.idValue

class PostRepositoryImpl(
    db: CoroutineDatabase
) : PostRepository {

    private val post = db.getCollection<Post>()

    override suspend fun insertOne(post: Post): Boolean {
        return this.post.insertOne(post).idValue != null
    }

    override suspend fun updateCommentCount(postId: String, i: Int) {
        post.updateOneById(
            postId,
            inc(Post::commentCount, i)
        )
    }

    override suspend fun findAll(): List<Post> {
        return this.post.find().descendingSort(Post::timestamp).toList()
    }

    override suspend fun getOneById(postId: String): Post? {
        return this.post.findOneById(postId)
    }

    override suspend fun updateLikeCount(postId: String, i: Int) {
        post.updateOneById(
            postId,
            inc(Post::likeCount, i)
        )
    }

    override suspend fun findAllByUser(userId: String): List<ProfilePostResponse> {
        val postImages: MutableList<ProfilePostResponse> = mutableListOf()

        this.post.find(Post::userId eq userId).toList().forEach { post ->
            postImages.add(ProfilePostResponse(
                id = post.id,
                imageBase64 = post.imageBase64
            ))
        }

        return postImages
    }
}