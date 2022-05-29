package com.anthonylldev.user.infrastructure.rest

import com.anthonylldev.user.application.ProfileResponse
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userController(
    userService: UserService
) {

    authenticate {

        get("/user/profile/{userId}") {
            val userIdByRequest: String = call.parameters["userId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val profileResponse: ProfileResponse? = userService.loadProfile(userIdByRequest, call.userId)

            if (profileResponse == null) {
                call.respond(
                    HttpStatusCode.NotFound
                )
            } else {
                call.respond(
                    HttpStatusCode.OK,
                    profileResponse
                )
            }
        }
    }
}