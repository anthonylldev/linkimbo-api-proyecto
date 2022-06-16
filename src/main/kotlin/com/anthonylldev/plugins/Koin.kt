package com.anthonylldev.plugins

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.application.service.impl.AuthServiceImpl
import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.authentication.infrastructure.repository.AuthRepositoryImpl
import com.anthonylldev.follow.application.service.FollowService
import com.anthonylldev.follow.application.service.impl.FollowServiceImpl
import com.anthonylldev.follow.domain.FollowRepository
import com.anthonylldev.follow.infrastructure.repository.FollowRepositoryImpl
import com.anthonylldev.post.application.service.PostService
import com.anthonylldev.post.application.service.impl.PostServiceImpl
import com.anthonylldev.post.domain.repository.PostRepository
import com.anthonylldev.post.infrastructure.repository.PostRepositoryImpl
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.application.service.impl.UserServiceImpl
import com.anthonylldev.user.domain.UserRepository
import com.anthonylldev.user.infrastructure.repository.UserRepositoryImpl
import com.anthonylldev.util.Constants
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import com.anthonylldev.like.application.service.PostLikeService
import com.anthonylldev.like.application.service.impl.PostLikeServiceImpl
import com.anthonylldev.like.domain.repository.PostLikeRepository
import com.anthonylldev.like.infrastructure.repository.PostLikeRepositoryImpl
import org.slf4j.LoggerFactory

fun Application.configureKoin() {
    install(Koin) {
        modules(module {

            val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
            val rootLogger = loggerContext.getLogger("org.mongodb.driver")
            rootLogger.level = Level.OFF

            single {
                val client = KMongo.createClient().coroutine
                client.getDatabase(Constants.DATABASE_NAME)
            }

            single<AuthRepository> { AuthRepositoryImpl(get()) }
            single<UserRepository> { UserRepositoryImpl(get()) }
            single<FollowRepository> { FollowRepositoryImpl(get()) }
            single<PostLikeRepository> { PostLikeRepositoryImpl(get()) }
            single<PostRepository> { PostRepositoryImpl(get()) }

            single<AuthService> { AuthServiceImpl(get()) }
            single<UserService> { UserServiceImpl(get(), get()) }
            single<FollowService> { FollowServiceImpl(get(), get()) }
            single<PostLikeService> { PostLikeServiceImpl(get(), get()) }
            single<PostService> { PostServiceImpl(get(), get(), get()) }

        })
    }
}