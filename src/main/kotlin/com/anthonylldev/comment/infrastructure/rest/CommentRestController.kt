package com.anthonylldev.comment.infrastructure.rest

import com.anthonylldev.comment.application.data.PostCommentRequest
import com.anthonylldev.comment.application.service.PostCommentService
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.commentRestController(
    postCommentService: PostCommentService
) {

    authenticate {

        post("/post/{postId}/comment") {
            val request = call.receiveOrNull<PostCommentRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val postIdByRequest: String = call.parameters["postId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val result = postCommentService.comment(call.userId, postIdByRequest, request)

            if (result) {
                call.respond(HttpStatusCode.OK)
                return@post
            } else {
                call.respond(HttpStatusCode.NotFound)
                return@post
            }
        }

        get("/post/{postId}/comment") {
            val postIdByRequest: String = call.parameters["postId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val allCommentsByPost = postCommentService.getAllPostCommentsByPost(postIdByRequest)

            if (allCommentsByPost == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(HttpStatusCode.OK, allCommentsByPost)
            }
        }
    }
}