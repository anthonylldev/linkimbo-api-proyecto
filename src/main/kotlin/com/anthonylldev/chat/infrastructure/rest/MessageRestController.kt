package com.anthonylldev.chat.infrastructure.rest

import com.anthonylldev.chat.application.service.MessageService
import com.anthonylldev.chat.domain.model.Message
import com.anthonylldev.util.userId
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.messageRestController(
    messageService: MessageService
) {
    authenticate {

        post("/chat/{userId}") {
            val request = call.receiveOrNull<Message>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            val userIdByRequest: String = call.parameters["userId"] ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            if (request.userEmitterId != call.callId) {
                call.respond(HttpStatusCode.Unauthorized)
                return@post
            }

            if (request.userReceiverId != userIdByRequest) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            messageService.insertMessage(request)
            call.respond(HttpStatusCode.OK)
        }

        get("/chat") {
            val didUserExist = messageService.getAllMessagesByUserId(call.userId)

            if (didUserExist == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}