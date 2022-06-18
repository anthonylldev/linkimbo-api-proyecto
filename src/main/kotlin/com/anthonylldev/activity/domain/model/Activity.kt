package com.anthonylldev.activity.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Activity(
    @BsonId
    val id: String = ObjectId.get().toString(),
    val userId: String,
    val actionType: String,
    val timestamp: Long
)
