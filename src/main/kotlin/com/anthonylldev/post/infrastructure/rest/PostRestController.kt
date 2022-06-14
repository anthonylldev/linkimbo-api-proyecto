package com.anthonylldev.post.infrastructure.rest

import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.post.domain.Post
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
            val request = call.receiveOrNull<Post>() ?: kotlin.run {
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

            val allPostSortByDate = postService.getAllPostSortByDate()
            call.respond(HttpStatusCode.OK, allPostSortByDate)

            /*if (allPostSortByDate.isEmpty()) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            } else {
                call.respond(HttpStatusCode.OK, allPostSortByDate)
                return@get
            }*/
        }

    }
}