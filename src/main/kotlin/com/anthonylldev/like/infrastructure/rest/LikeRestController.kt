package com.anthonylldev.like.infrastructure.rest

import com.anthonylldev.like.application.data.PostLikeRequest
import com.anthonylldev.like.application.service.PostLikeService
import com.anthonylldev.user.domain.User
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.likeRestController(
    postLikeService: PostLikeService
) {

    authenticate {

        post("/post/{postId}/like") {
            val request = call.receiveOrNull<PostLikeRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val postIdByRequest: String = call.parameters["postId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val result = if (request.isLiked) {
                postLikeService.like(call.userId, postIdByRequest)
            } else {
                postLikeService.unLike(call.userId, postIdByRequest)
            }

            if (result) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}