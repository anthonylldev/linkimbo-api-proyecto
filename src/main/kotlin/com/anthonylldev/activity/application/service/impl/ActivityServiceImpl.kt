package com.anthonylldev.activity.application.service.impl

import com.anthonylldev.activity.application.data.ActivityRequest
import com.anthonylldev.activity.application.data.ActivityResponse
import com.anthonylldev.activity.application.service.ActivityService
import com.anthonylldev.activity.domain.repository.ActivityRepository
import com.anthonylldev.user.domain.UserRepository

class ActivityServiceImpl(
    private val activityRepository: ActivityRepository,
    private val userRepository: UserRepository
) : ActivityService {

    override suspend fun getActivities(userId: String): List<ActivityResponse>? {
        val user = userRepository.getUserById(userId) ?: return null

        val activityResponse: MutableList<ActivityResponse> = mutableListOf()

        this.activityRepository.findAllByIdSortByTimestamp(userId).forEach { activity ->

            activityResponse.add(
                ActivityResponse(
                    userId = user.id,
                    userName = user.username,
                    timestamp = activity.timestamp,
                    actionType = activity.actionType
                )
            )
        }

        return activityResponse
    }

    override suspend fun insertIfUserExist(request: ActivityRequest): Boolean {
        val user = userRepository.getUserById(request.userId) ?: return false

        this.activityRepository.insertOne(request)

        return true
    }

}