package io.winapps.journeyapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UsernameViewModel : ViewModel() {
    var username = mutableStateOf("")
    var isUsernameAvailable = mutableStateOf<Boolean?>(null)
    var errorMessage = mutableStateOf<String?>(null)
    var isUsernameValid = mutableStateOf<Boolean?>(null)
    var isLoginVisible = mutableStateOf(false)

    init {
        viewModelScope.launch {
            snapshotFlow { username.value }
                .debounce(1000)
                .distinctUntilChanged()
                .filter { it.isNotBlank() }
                .collect { newUsername ->
                    isUsernameValid.value = checkIsUsernameValid(newUsername)
                    if (!isLoginVisible.value) {
                        try {
                            val available = checkUsernameAvailability(newUsername)
                            isUsernameAvailable.value = available
                            errorMessage.value = if (available) null else "Username is taken... Please choose another."
                        } catch (e: Exception) {
                            errorMessage.value = "Server error: ${e.localizedMessage}"
                            isUsernameAvailable.value = null
                        }
                    }
                }
        }
    }

    fun toggleIsLoginVisible() {
        isLoginVisible.value = !isLoginVisible.value
    }

    private fun checkIsUsernameValid(username: String): Boolean {
        return true // update this later... figure out regex for it
    }

    private suspend fun checkUsernameAvailability(username: String): Boolean {

        return true // um...
    }
}