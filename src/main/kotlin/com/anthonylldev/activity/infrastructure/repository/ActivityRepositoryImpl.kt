package com.anthonylldev.activity.infrastructure.repository

import com.anthonylldev.activity.application.data.ActivityRequest
import com.anthonylldev.activity.domain.model.Activity
import com.anthonylldev.activity.domain.repository.ActivityRepository
import org.litote.kmongo.ascending
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ActivityRepositoryImpl(
    db: CoroutineDatabase
) : ActivityRepository {

    private val activity = db.getCollection<Activity>()

    override suspend fun findAllByIdSortByTimestamp(userId: String): List<Activity> {
        return this.activity.find(
            Activity::userId eq userId
        ).sort(
            ascending(
                Activity::timestamp
            )
        ).toList()
    }

    override suspend fun insertOne(request: ActivityRequest) {
        this.activity.insertOne(
            Activity(
                userId = request.userId,
                actionType = request.actionType,
                timestamp = request.timestamp
            )
        )
    }
}