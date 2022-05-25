package com.anthonylldev.plugins

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.infrastructure.rest.authenticationController
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val authenticationService: AuthService by inject()

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()


    routing {
        authenticationController(
            authService = authenticationService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret
        )
    }
}
