package com.anthonylldev.plugins

import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(module {

        })
    }
}