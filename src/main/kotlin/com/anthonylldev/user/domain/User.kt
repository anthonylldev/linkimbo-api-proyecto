package com.anthonylldev.user.domain

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val username: String,
    val email: String,
    val password: String,
    val realName: String? = "",
    val imageBase64: String? = null,
    val description: String? = "",
    val website: String? = "",
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val postCount: Int = 0,
)