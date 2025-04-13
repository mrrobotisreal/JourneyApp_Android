package io.winapps.journeyapp.repository

import io.winapps.journeyapp.local.SecureStorage
import io.winapps.journeyapp.models.CreateAccountRequest
import io.winapps.journeyapp.models.CreateAccountResponse
import io.winapps.journeyapp.models.LoginRequest
import io.winapps.journeyapp.models.LoginResponse
import io.winapps.journeyapp.services.Api
import io.winapps.journeyapp.services.ApiClient
import io.winapps.journeyapp.services.ApiService
import io.winapps.journeyapp.services.NetworkService

class ApiRepository(private val secureStorage: SecureStorage) {
    private val apiService: ApiService = Api.service

    suspend fun createAccount(username: String, password: String, sessionOption: String): CreateAccountResponse {
        val request = CreateAccountRequest(username, password, sessionOption)
        val response = apiService.createAccount(request)

        secureStorage.saveApiKey(response.apiKey)
        secureStorage.saveUserId(response.userId)
        secureStorage.saveUsername(response.username)
        secureStorage.saveToken(response.token)

        return response
    }

    suspend fun login(username: String, password: String, sessionOption: String): LoginResponse {
        val request = LoginRequest(username, password, sessionOption)
        val response = apiService.login(request)

        secureStorage.saveApiKey(response.apiKey)
        secureStorage.saveUserId(response.userId)
        secureStorage.saveUsername(response.username)
        secureStorage.saveToken(response.token)

        return response
    }

    suspend fun checkUsernameAvailability(username: String): Boolean {
        val requestBody = mapOf("username" to username)
        val response = apiService.validateUsername(requestBody)

        return response.usernameAvailable
    }
}