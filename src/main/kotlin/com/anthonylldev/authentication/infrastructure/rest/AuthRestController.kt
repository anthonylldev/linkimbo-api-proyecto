package com.anthonylldev.authentication.infrastructure.rest

import com.anthonylldev.authentication.application.Token
import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.domain.CreateAccount
import com.anthonylldev.authentication.domain.Login
import com.anthonylldev.user.domain.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.response.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Route.authenticationController(
    authService: AuthService,
    jwtIssuer: String,
    jwtAudience: String,
    jwtSecret: String
) {

    fun generateToken(userId: String): Token {
        val expiresIn = 1000L * 60L * 60L * 24L * 365L

        val token = JWT.create()
            .withClaim("userId", userId)
            .withIssuer(jwtIssuer)
            .withExpiresAt(Date(System.currentTimeMillis() + expiresIn))
            .withAudience(jwtAudience)
            .sign(Algorithm.HMAC256(jwtSecret))

        return Token(token)
    }

    post("/login") {
        val request = call.receiveOrNull<Login>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user: User? = authService.login(request)

        if (user == null) {
            call.respond(HttpStatusCode.Unauthorized)
            return@post
        }

        call.respond(HttpStatusCode.OK, generateToken(user.id))
        return@post
    }

    post("/createAccount") {
        val request = call.receiveOrNull<CreateAccount>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user: User? = authService.createAccount(request)

        if (user == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        call.respond(HttpStatusCode.OK, generateToken(user.id))
    }

    authenticate {
        get("/authenticate") {
            call.respond(HttpStatusCode.OK)
            return@get
        }
    }
}