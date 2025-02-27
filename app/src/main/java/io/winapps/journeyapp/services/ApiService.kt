package io.winapps.journeyapp.services

import io.winapps.journeyapp.models.CreateAccountRequest
import io.winapps.journeyapp.models.CreateAccountResponse
import io.winapps.journeyapp.models.LoginRequest
import io.winapps.journeyapp.models.LoginResponse
import io.winapps.journeyapp.models.UsernameValidationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/create")
    suspend fun createAccount(@Body request: CreateAccountRequest): CreateAccountResponse

    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("validate/username")
    suspend fun validateUsername(@Body request: Map<String, String>): UsernameValidationResponse
}