package com.anthonylldev.plugins

import com.anthonylldev.activity.application.service.ActivityService
import com.anthonylldev.activity.infrastructure.rest.activityRestController
import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.infrastructure.rest.authenticationController
import com.anthonylldev.chat.application.service.MessageService
import com.anthonylldev.chat.infrastructure.rest.messageRestController
import com.anthonylldev.comment.application.service.PostCommentService
import com.anthonylldev.comment.infrastructure.rest.commentRestController
import com.anthonylldev.follow.application.service.FollowService
import com.anthonylldev.follow.infrastructure.rest.followController
import com.anthonylldev.like.application.service.comment.CommentLikeService
import com.anthonylldev.like.application.service.post.PostLikeService
import com.anthonylldev.like.infrastructure.rest.likeRestController
import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.post.infrastructure.rest.postController
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.infrastructure.rest.userController
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val authenticationService: AuthService by inject()
    val userService: UserService by inject()
    val followService: FollowService by inject()
    val postService: PostService by inject()
    val postLikeService: PostLikeService by inject()
    val postCommentService: PostCommentService by inject()
    val commentLikeService: CommentLikeService by inject()
    val activityService: ActivityService by inject()
    val messageService: MessageService by inject()

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
        postController(postService = postService)
        likeRestController(
            postLikeService = postLikeService,
            commentLikeService = commentLikeService
        )
        commentRestController(postCommentService = postCommentService)
        activityRestController(activityService = activityService)
        messageRestController(messageService = messageService)
    }
}
