package com.anthonylldev.post.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    @BsonId
    val id: String = ObjectId().toString(),
    val userId: String,
    val imageBase64: String,
    val description: String,
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val timestamp: Long
)
