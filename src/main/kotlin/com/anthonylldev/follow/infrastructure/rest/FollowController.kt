package com.anthonylldev.follow.infrastructure.rest

import com.anthonylldev.follow.application.service.FollowService
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.domain.User
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.followController(
    followService: FollowService
) {

    authenticate {

        post("/user/profile/{userId}/follow") {
            val userIdByRequest: String = call.parameters["userId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val didUserExist = followService.followUserIfExists(call.userId, followedUserId = userIdByRequest)

            if (didUserExist) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }


        post("/user/profile/{userId}/unfollow") {
            val userIdByRequest: String = call.parameters["userId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val didUserExist = followService.unFollowUserIfExists(call.userId, unFollowedUserId = userIdByRequest)

            if (didUserExist) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}