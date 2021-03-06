package com.anthonylldev.like.domain.comment.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class CommentLike(
    @BsonId
    val id: String = ObjectId.get().toString(),
    val userId: String,
    val postId: String,
    val commentId: String
)
