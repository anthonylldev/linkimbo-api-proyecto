package com.anthonylldev.activity.application.data

data class ActivityResponse(
    val userId: String,
    val userName : String,
    val actionType: String,
    val timestamp: Long
)