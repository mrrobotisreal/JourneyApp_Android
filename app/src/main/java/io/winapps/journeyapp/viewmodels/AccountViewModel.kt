package io.winapps.journeyapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.winapps.journeyapp.models.CreateAccountResponse
import io.winapps.journeyapp.models.LoginResponse
import io.winapps.journeyapp.repository.ApiRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val isUsernameAvailable = mutableStateOf<Boolean?>(null)
    val errorMessage = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val showSuccessMessage = mutableStateOf(false)
    val isCheckingAvailability = mutableStateOf(false)
    val isLoginVisible = mutableStateOf(false)
    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
    var createAccountState by mutableStateOf<CreateAccountState>(CreateAccountState.Idle)

    private val usernameFlow = MutableStateFlow("")

    private var debouncedCheckJob: Job? = null

    init {
        setupUsernameDebounce()
    }

    fun toggleIsLoginVisible() {
        isLoginVisible.value = !isLoginVisible.value
        isUsernameAvailable.value = null
        errorMessage.value = ""
    }

    @OptIn(FlowPreview::class)
    private fun setupUsernameDebounce() {
        viewModelScope.launch {
            usernameFlow
                .debounce(1000)
                .distinctUntilChanged()
                .filter { it.isNotBlank() && !isLoginVisible.value }
                .collectLatest { username ->
                    performUsernameCheck(username)
                }
        }
    }

    fun updateUsername(newUsername: String) {
        username.value = newUsername
        usernameFlow.value = newUsername
    }

    private suspend fun performUsernameCheck(username: String) {
        try {
            isCheckingAvailability.value = true
            val available = repository.checkUsernameAvailability(username)
            isUsernameAvailable.value = available
            errorMessage.value = if (available) "" else "Username is taken... Please choose another."
        } catch (e: Exception) {
            errorMessage.value = "Server error: ${e.localizedMessage}"
            isUsernameAvailable.value = null
        } finally {
            isCheckingAvailability.value = false
        }
    }

    fun checkUsernameAvailability() {
        viewModelScope.launch {
            if (username.value.isNotBlank()) {
                performUsernameCheck(username.value)
            }
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            isLoading.value = true
            createAccountState = CreateAccountState.Loading
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
                    createAccountState = CreateAccountState.Success(response)
                } else {
                    errorMessage.value = "Failed to create account. Try again later."
                    createAccountState = CreateAccountState.Error("Failed to create account. Try again later.")
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.localizedMessage}"
                createAccountState = CreateAccountState.Error("Error: ${e.localizedMessage}")
            } finally {
                isLoading.value = false
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            isLoading.value = true
            loginState = LoginState.Loading
            try {
                val response = repository.login(username.value, password.value, "always")
                if (response.success) {
                    showSuccessMessage.value = true
                    loginState = LoginState.Success(response)
                } else {
                    errorMessage.value = "Login failed. Check your credentials."
                    loginState = LoginState.Error("Login failed. Check your credentials.")
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.localizedMessage}"
                loginState = LoginState.Error("Error: ${e.localizedMessage}")
            } finally {
                isLoading.value = false
            }
        }
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val response: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}

sealed class CreateAccountState {
    data object Idle : CreateAccountState()
    data object Loading : CreateAccountState()
    data class Success(val response: CreateAccountResponse) : CreateAccountState()
    data class Error(val message: String) : CreateAccountState()
}