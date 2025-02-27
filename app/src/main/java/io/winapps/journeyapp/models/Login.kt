package io.winapps.journeyapp.models

data class LoginRequest(
    val username: String,
    val password: String,
    val sessionOption: String
)

data class LoginResponse(
    val success: Boolean,
    val userId: String,
    val username: String,
    val token: String,
    val apiKey: String,
    val font: String
)
