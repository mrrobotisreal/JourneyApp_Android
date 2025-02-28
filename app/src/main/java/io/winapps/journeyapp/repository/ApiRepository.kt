package io.winapps.journeyapp.repository

import io.winapps.journeyapp.models.CreateAccountRequest
import io.winapps.journeyapp.models.CreateAccountResponse
import io.winapps.journeyapp.models.LoginRequest
import io.winapps.journeyapp.models.LoginResponse
import io.winapps.journeyapp.services.ApiService
import io.winapps.journeyapp.services.NetworkService

class ApiRepository {
    private val apiService: ApiService = NetworkService.api

    suspend fun createAccount(username: String, password: String, sessionOption: String): CreateAccountResponse {
        val request = CreateAccountRequest(username, password, sessionOption)
        return apiService.createAccount(request)
    }

    suspend fun login(username: String, password: String, sessionOption: String): LoginResponse {
        val request = LoginRequest(username, password, sessionOption)
        return apiService.login(request)
    }

    suspend fun checkUsernameAvailability(username: String): Boolean {
        val requestBody = mapOf("username" to username)
        val response = apiService.validateUsername(requestBody)
        return response.usernameAvailable
    }
}