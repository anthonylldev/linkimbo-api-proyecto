package com.anthonylldev.activity.infrastructure.rest

import com.anthonylldev.activity.application.data.ActivityRequest
import com.anthonylldev.activity.application.data.ActivityResponse
import com.anthonylldev.activity.application.service.ActivityService
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.activityRestController(
    activityService: ActivityService
) {

    authenticate {

        post("/activity") {
            val request = call.receiveOrNull<ActivityRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if (activityService.insertIfUserExist(request)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/activity") {
            val activitiesIfUserExist: List<ActivityResponse>? = activityService.getActivities(call.userId)

            if (activitiesIfUserExist == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(HttpStatusCode.OK, activitiesIfUserExist)
            }
        }
    }
}