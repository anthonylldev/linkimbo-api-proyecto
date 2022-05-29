package com.anthonylldev.plugins

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.infrastructure.rest.authenticationController
import com.anthonylldev.follow.application.service.FollowService
import com.anthonylldev.follow.infrastructure.rest.followController
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.infrastructure.rest.userController
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.auth.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val authenticationService: AuthService by inject()
    val userService: UserService by inject()
    val followService: FollowService by inject()

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


            userController(userService = userService)
            followController(followService = followService)

    }
}
