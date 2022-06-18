package com.anthonylldev.comment.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class PostComment(
    @BsonId
    val id: String = ObjectId.get().toString(),
    val userId: String,
    val postId: String,
    val timestamp: Long,
    val comment: String,
    val likeCount: Int = 0,
)
