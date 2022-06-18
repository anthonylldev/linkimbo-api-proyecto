package com.anthonylldev.activity.application.service

import com.anthonylldev.activity.application.data.ActivityRequest
import com.anthonylldev.activity.application.data.ActivityResponse

interface ActivityService {
    suspend fun getActivities(userId: String): List<ActivityResponse>?
    suspend fun insertIfUserExist(request: ActivityRequest): Boolean
}