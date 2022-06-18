package com.anthonylldev.post.infrastructure.rest

import com.anthonylldev.post.application.data.PostRequest
import com.anthonylldev.post.application.data.PostResponse
import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.postController(
    postService: PostService
) {

    authenticate {

        post("/post") {
            val request = call.receiveOrNull<PostRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest, it)
                return@post
            }

            if (request.userId != call.userId) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val didUserExist = postService.createPostIfUserExist(request)

            if (didUserExist) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/post") {
            val allPostSortByDate = postService.getAllPostSortByDate(call.userId)
            call.respond(HttpStatusCode.OK, allPostSortByDate)
        }

        get("/post/{postId}") {
            val postIdByRequest: String = call.parameters["postId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val post: PostResponse? = postService.getPost(call.userId, postIdByRequest)

            if (post != null) {
                call.respond(
                    HttpStatusCode.OK,
                    post
                )
            } else {
                call.respond(
                    HttpStatusCode.NotFound
                )
            }
        }
    }
}