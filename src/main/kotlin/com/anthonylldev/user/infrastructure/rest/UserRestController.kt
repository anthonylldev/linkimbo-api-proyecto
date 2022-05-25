package com.anthonylldev.user.infrastructure.rest

import com.anthonylldev.user.application.ProfileResponse
import com.anthonylldev.user.application.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userController(
    userService: UserService
) {

    authenticate {
        get("/user/profile/{userId}") {

            val request: String? = call.parameters["userId"]
            val userIdByToken: String = call.principal<JWTPrincipal>()?.getClaim("userId", String::class)!!

            if (request == null || request.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val profileResponse: ProfileResponse? = userService.loadProfile(request, userIdByToken)

            if (profileResponse == null) {
                call.respond(
                    HttpStatusCode.NotFound
                )
                return@get
            } else {
                call.respond(
                    HttpStatusCode.OK,
                    profileResponse
                )
                return@get
            }
        }
    }
}