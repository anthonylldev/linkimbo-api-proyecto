package com.anthonylldev.activity.domain.repository

import com.anthonylldev.activity.application.data.ActivityRequest
import com.anthonylldev.activity.domain.model.Activity

interface ActivityRepository {
    suspend fun findAllByIdSortByTimestamp(userId: String): List<Activity>
    suspend fun insertOne(request: ActivityRequest)
}