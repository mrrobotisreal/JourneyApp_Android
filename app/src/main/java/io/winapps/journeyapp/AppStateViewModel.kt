package io.winapps.journeyapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.winapps.journeyapp.local.SecureStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppStateViewModel @Inject constructor(
    private val secureStorage: SecureStorage
): ViewModel() {
    var userId by mutableStateOf<String?>(null)
    var username by mutableStateOf<String?>(null)
    var apiKey by mutableStateOf<String?>(null)
    var jwt by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)
    var didFinishSplash by mutableStateOf(false)
    var selectedEntryId by mutableStateOf("")
//    var allEntries by mutableStateOf<List<String>>(emptyList()) <- needs to be updated with type

    init {
        viewModelScope.launch {
            val storedUserId = secureStorage.getUserId()
            val storedUsername = secureStorage.getUsername()
            val storedApiKey = secureStorage.getApiKey()
            val storedToken = secureStorage.getToken()

            if (storedUserId != null && storedUsername != null && storedApiKey != null && storedToken != null) {
                userId = storedUserId
                username = storedUsername
                apiKey = storedApiKey
                jwt = storedToken
                didFinishSplash = true
                isLoggedIn = true
            } else {
                delay(1000L)
                didFinishSplash = true
            }
        }
    }

    fun markLoggedIn() {
        isLoggedIn = true
    }

    fun markLoggedOut() {
        isLoggedIn = false
    }

    fun setUserData(userId: String, username: String, apiKey: String, token: String) {
        this.userId = userId
        this.username = username
        secureStorage.saveUserId(userId)
        secureStorage.saveUsername(username)
        secureStorage.saveApiKey(apiKey)
        secureStorage.saveToken(token)
    }

    fun getStoredUserId(): String? {
        return secureStorage.getUserId()
    }

    fun getStoredUsername(): String? {
        return secureStorage.getUsername()
    }

    fun getStoredApiKey(): String? {
        return secureStorage.getApiKey()
    }

    fun getStoredToken(): String? {
        return secureStorage.getToken()
    }

    // TODO: implement later for switching between viewing, creating, editing, settings etc...
//    fun switchCurrentAppScreen(screen: AppScreen) {
//        this.currentAppScreen = screen
//    }
}