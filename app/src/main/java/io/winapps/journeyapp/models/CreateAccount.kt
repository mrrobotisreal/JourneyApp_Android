package io.winapps.journeyapp.models

data class CreateAccountRequest(
    val username: String,
    val password: String,
    val sessionOption: String
)

data class CreateAccountResponse(
    val success: Boolean,
    val userId: String,
    val username: String,
    val token: String,
    val apiKey: String,
    val font: String
)

data class UsernameValidationResponse(
    val usernameAvailable: Boolean
)
