package com.anthonylldev.plugins

import com.anthonylldev.authentication.application.service.AuthService
import com.anthonylldev.authentication.application.service.impl.AuthServiceImpl
import com.anthonylldev.authentication.domain.AuthRepository
import com.anthonylldev.authentication.infrastructure.repository.AuthRepositoryImpl
import com.anthonylldev.util.Constants
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
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

        })
    }
}