package com.anthonylldev.follow.domain

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Follow(
    @BsonId
    val id: String = ObjectId().toString(),
    val userId: String,
    val followedUserId: String
)

