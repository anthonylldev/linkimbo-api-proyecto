package com.anthonylldev.plugins

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.application.service.impl.AuthServiceImpl
import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.authentication.infrastructure.repository.AuthRepositoryImpl
import com.anthonylldev.follow.application.service.FollowService
import com.anthonylldev.follow.application.service.impl.FollowServiceImpl
import com.anthonylldev.follow.domain.FollowRepository
import com.anthonylldev.follow.infrastructure.repository.FollowRepositoryImpl
import com.anthonylldev.user.application.service.UserService
import com.anthonylldev.user.application.service.impl.UserServiceImpl
import com.anthonylldev.user.domain.UserRepository
import com.anthonylldev.user.infrastructure.repository.UserRepositoryImpl
import com.anthonylldev.util.Constants
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureKoin() {
    install(Koin) {
        modules(module {

            single {
                val client = KMongo.createClient().coroutine
                client.getDatabase(Constants.DATABASE_NAME)
            }

            single<AuthRepository> { AuthRepositoryImpl(get()) }

            single<AuthService> { AuthServiceImpl(get()) }

            single<UserRepository> { UserRepositoryImpl(get()) }

            single<UserService> { UserServiceImpl(get(), get()) }

            single<FollowRepository> { FollowRepositoryImpl(get()) }

            single<FollowService> { FollowServiceImpl(get(), get()) }

        })
    }
}