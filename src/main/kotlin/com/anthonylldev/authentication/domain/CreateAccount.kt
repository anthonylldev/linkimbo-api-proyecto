package com.anthonylldev.authentication.domain

data class CreateAccount(
    val username: String,
    val email: String,
    val password: String
)
