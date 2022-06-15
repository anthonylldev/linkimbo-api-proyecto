package com.anthonylldev.like.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class PostLike(
    @BsonId
    val id: String = ObjectId.get().toString(),
    val userId: String,
    val postId: String
)
