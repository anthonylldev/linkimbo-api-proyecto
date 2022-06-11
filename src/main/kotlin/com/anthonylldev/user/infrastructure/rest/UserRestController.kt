package com.anthonylldev.user.infrastructure.rest

import com.anthonylldev.authentication.domain.Login
import com.anthonylldev.user.application.ProfileResponse
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.domain.User
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
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

        get("/user") {
            val user: User? = userService.getUser(call.userId)

            if (user != null) {
                call.respond(
                    HttpStatusCode.OK,
                    user
                )
            } else {
                call.respond(
                    HttpStatusCode.NotFound
                )
            }
        }

        put("/user") {
            val request = call.receiveOrNull<User>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@put
            }

            val profileUpdate: User? = userService.updateUser(request, call.userId)

            if (profileUpdate == null) {
                call.respond(
                    HttpStatusCode.NotFound
                )
            } else {
                call.respond(
                    HttpStatusCode.OK
                )
            }
        }
    }
}