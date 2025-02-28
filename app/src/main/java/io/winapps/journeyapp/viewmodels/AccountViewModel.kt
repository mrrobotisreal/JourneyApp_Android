package io.winapps.journeyapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.winapps.journeyapp.repository.ApiRepository
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: ApiRepository) : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val isUsernameAvailable = mutableStateOf<Boolean?>(null)
    val errorMessage = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val showSuccessMessage = mutableStateOf(false)
    val isLoginVisible = mutableStateOf(false)

    fun toggleIsLoginVisible() {
        isLoginVisible.value = !isLoginVisible.value
    }

    fun checkUsernameAvailability() {
        viewModelScope.launch {
            if (username.value.isNotBlank()) {
                try {
                    val available = repository.checkUsernameAvailability(username.value)
                    isUsernameAvailable.value = available
                    errorMessage.value = if (available) "" else "Username is taken... Please choose another."
                } catch (e: Exception) {
                    errorMessage.value = "Server error: ${e.localizedMessage}"
                    isUsernameAvailable.value = null
                }
            }
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val available = repository.checkUsernameAvailability(username.value)
                if (!available) {
                    errorMessage.value = "Username already taken."
                    isLoading.value = false
                    return@launch
                }
                val response = repository.createAccount(username.value, password.value, "always")
                if (response.success) {
                    showSuccessMessage.value = true
                    // TODO: Update global state here
                } else {
                    errorMessage.value = "Failed to create account. Try again later."
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = repository.login(username.value, password.value, "always")
                if (response.success) {
                    showSuccessMessage.value = true
                } else {
                    errorMessage.value = "Login failed. Check your credentials."
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }
}