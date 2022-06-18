package com.anthonylldev.like.infrastructure.rest

import com.anthonylldev.like.application.data.LikeRequest
import com.anthonylldev.like.application.service.comment.CommentLikeService
import com.anthonylldev.like.application.service.post.PostLikeService
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.likeRestController(
    postLikeService: PostLikeService,
    commentLikeService: CommentLikeService
) {

    authenticate {

        post("/post/{postId}/like") {
            val request = call.receiveOrNull<LikeRequest>() ?: kotlin.run {
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

        post("/post/{postId}/comment/{commentId}/like") {
            val request = call.receiveOrNull<LikeRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val postIdByRequest: String = call.parameters["postId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val commentIdByRequest: String = call.parameters["commentId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val result = if (request.isLiked) {
                commentLikeService.like(call.userId, postIdByRequest, commentIdByRequest)
            } else {
                commentLikeService.unLike(call.userId, postIdByRequest, commentIdByRequest)
            }

            if (result) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}