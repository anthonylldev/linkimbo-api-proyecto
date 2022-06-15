package com.anthonylldev.like.infrastructure.rest

import io.ktor.auth.*
import io.ktor.routing.*

fun Route.likeRestController(

) {

    authenticate {

        post("/post/{postId}/like") {

        }
    }
}