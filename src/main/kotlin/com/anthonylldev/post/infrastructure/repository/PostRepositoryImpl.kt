package com.anthonylldev.post.infrastructure.repository

import com.anthonylldev.post.domain.model.Post
import com.anthonylldev.post.domain.repository.PostRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.util.idValue

class PostRepositoryImpl(
    db: CoroutineDatabase
) : PostRepository {

    private val post = db.getCollection<Post>()

    override suspend fun insertOne(post: Post): Boolean {
        return this.post.insertOne(post).idValue != null
    }

    override suspend fun findAll(): List<Post> {
        return this.post.find().descendingSort(Post::timestamp).toList()
    }
}