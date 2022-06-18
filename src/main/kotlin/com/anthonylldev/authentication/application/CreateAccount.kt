package com.anthonylldev.authentication.application

data class CreateAccount(
    val username: String,
    val email: String,
    val password: String
)
