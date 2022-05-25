package com.anthonylldev.plugins

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

        })
    }
}